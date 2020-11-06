package com.pvale.project.csc.ws.advice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.pvale.project.csc.api.enumerator.CscApiErrorType;
import com.pvale.project.csc.api.enumerator.CscApiRequestParameter;
import com.pvale.project.csc.api.exception.CscCredentialDisabledException;
import com.pvale.project.csc.api.exception.CscInvalidOtpException;
import com.pvale.project.csc.api.exception.CscInvalidParameterException;
import com.pvale.project.csc.api.exception.CscInvalidParameterValueException;
import com.pvale.project.csc.api.exception.CscInvalidPinException;
import com.pvale.project.csc.api.exception.CscNumberSignaturesTooHighException;
import com.pvale.project.csc.api.exception.CscOtpLockedException;
import com.pvale.project.csc.api.exception.CscPinLockedException;
import com.pvale.project.csc.api.exception.CscServerErrorException;
import com.pvale.project.csc.api.response.CscApiErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice(basePackages = "com.pvale.project.csc.ws.controller")
public class CscApiControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(CscApiControllerAdvice.class);
    private static final Locale LOCALE = Locale.ENGLISH;
    private static final String NULL_CONSTRAINT_CODE = "Null";
    private static final String NOT_NULL_CONSTRAINT_CODE = "NotNull";
    public static final String ARRAY_PARAMETER_TYPE = "array";

    private MessageSource messageSource;

    @Autowired
    public CscApiControllerAdvice(@Qualifier("cscApiErrorMessages") MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({Exception.class, CscServerErrorException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CscApiErrorResponse handleException(Exception e) {
        LOGGER.error("Handling exception {}: {}", e.getClass().getSimpleName(), e.getMessage(), e);
        CscApiErrorType error = CscApiErrorType.SERVER_ERROR;
        String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
        return new CscApiErrorResponse(error, errorDescription);
    }

    /**
     * This method will try to determine the method parameter with error and build proper error response.
     * If it fails to do so, it'll just return the generic invalid request error
     *
     * @param e -> the input validation exception
     * @return the error response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CscApiErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        LOGGER.error("Handling exception {}: {}", e.getClass().getSimpleName(), e.getMessage(), e);

        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult == null) {
            CscApiErrorType error = CscApiErrorType.INVALID_REQUEST;
            String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
            return new CscApiErrorResponse(error, errorDescription);
        }

        FieldError fieldError = bindingResult.getFieldError();
        if (fieldError == null) {
            CscApiErrorType error = CscApiErrorType.INVALID_REQUEST;
            String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
            return new CscApiErrorResponse(error, errorDescription);
        }

        String fieldName = fieldError.getField();
        if (StringUtils.isBlank(fieldName)) {
            CscApiErrorType error = CscApiErrorType.INVALID_REQUEST;
            String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
            return new CscApiErrorResponse(error, errorDescription);
        }

        MethodParameter methodParameter = e.getParameter();
        if (methodParameter == null) {
            CscApiErrorType error = CscApiErrorType.INVALID_REQUEST;
            String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
            return new CscApiErrorResponse(error, errorDescription);
        }
        Class<?> classParameterType = methodParameter.getParameterType();
        if (classParameterType == null) {
            CscApiErrorType error = CscApiErrorType.INVALID_REQUEST;
            String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
            return new CscApiErrorResponse(error, errorDescription);
        }

        Field field;
        String parameterName;
        try {
            field = classParameterType.getDeclaredField(fieldName);

            if (field == null) {
                CscApiErrorType error = CscApiErrorType.INVALID_REQUEST;
                String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
                return new CscApiErrorResponse(error, errorDescription);
            }

            JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);

            // some parameters' names differ from the field java name
            if (jsonProperty != null) {
                parameterName = jsonProperty.value();
            } else {
                parameterName = fieldName;
            }

        } catch (NoSuchFieldException noSuchFieldException) {
            // unlikely to happen because field name is obtained from exception
            // which in turn takes the request DTO into account
            LOGGER.error("No such field: {}", fieldName, noSuchFieldException);
            CscApiErrorType error = CscApiErrorType.INVALID_REQUEST;
            String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
            return new CscApiErrorResponse(error, errorDescription);
        }

        if (StringUtils.equals(fieldError.getCode(), NULL_CONSTRAINT_CODE)) {
            CscApiErrorType error = CscApiErrorType.PARAMETER_NULL;
            String errorDescription = this.messageSource.getMessage(error.getApiError(), new String[]{parameterName}, LOCALE);
            return new CscApiErrorResponse(error, errorDescription);
        } else if (StringUtils.equals(fieldError.getCode(), NOT_NULL_CONSTRAINT_CODE)) {
            CscApiErrorType error = CscApiErrorType.MISSING_OR_INVALID_TYPE;
            Class<?> type = field.getType();
            String parameterType;
            if (Collection.class.isAssignableFrom(type)) {
                parameterType = ARRAY_PARAMETER_TYPE;
            } else {
                parameterType = type.getSimpleName().toLowerCase();
            }
            String errorDescription = this.messageSource.getMessage(error.getApiError(), new String[]{parameterType, parameterName}, LOCALE);
            return new CscApiErrorResponse(error, errorDescription);
        } else {
            CscApiErrorType error = CscApiErrorType.INVALID_REQUEST;
            String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
            return new CscApiErrorResponse(error, errorDescription);
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CscApiErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        LOGGER.error("Handling exception {}: {}", e.getClass().getSimpleName(), e.getMessage(), e);

        if (e.getCause() != null && !(e.getCause() instanceof MismatchedInputException)) {
            CscApiErrorType error = CscApiErrorType.INVALID_REQUEST;
            String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
            return new CscApiErrorResponse(error, errorDescription);
        }

        MismatchedInputException mismatchedInputException = (MismatchedInputException) e.getCause();

        List<JsonMappingException.Reference> path = mismatchedInputException.getPath();
        if (CollectionUtils.isEmpty(path)) {
            CscApiErrorType error = CscApiErrorType.INVALID_REQUEST;
            String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
            return new CscApiErrorResponse(error, errorDescription);
        }

        JsonMappingException.Reference reference = path.get(0);
        if (reference == null) {
            CscApiErrorType error = CscApiErrorType.INVALID_REQUEST;
            String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
            return new CscApiErrorResponse(error, errorDescription);
        }

        String parameterName = reference.getFieldName();

        Class<?> targetType = mismatchedInputException.getTargetType();
        if (targetType == null) {
            CscApiErrorType error = CscApiErrorType.INVALID_REQUEST;
            String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
            return new CscApiErrorResponse(error, errorDescription);
        }

        String parameterType;
        if (Collection.class.isAssignableFrom(targetType)) {
            parameterType = ARRAY_PARAMETER_TYPE;
        } else {
            String simpleName = targetType.getSimpleName();
            if (StringUtils.isBlank(simpleName)) {
                CscApiErrorType error = CscApiErrorType.INVALID_REQUEST;
                String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
                return new CscApiErrorResponse(error, errorDescription);
            }
            parameterType = simpleName.toLowerCase();
        }

        CscApiErrorType error = CscApiErrorType.MISSING_OR_INVALID_TYPE;
        String errorDescription = this.messageSource.getMessage(error.getApiError(), new String[]{parameterType, parameterName}, LOCALE);
        return new CscApiErrorResponse(error, errorDescription);
    }

    @ExceptionHandler(UnrecognizedPropertyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CscApiErrorResponse handleUnrecognizedPropertyException(UnrecognizedPropertyException e) {
        LOGGER.error("Handling exception {}: {}", e.getClass().getSimpleName(), e.getMessage(), e);
        CscApiErrorType error = CscApiErrorType.INVALID_REQUEST;
        String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
        return new CscApiErrorResponse(error, errorDescription);
    }

    @ExceptionHandler(CscInvalidParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CscApiErrorResponse handleCscInvalidParameterException(CscInvalidParameterException e) {
        LOGGER.error("Handling exception {}: {}", e.getClass().getSimpleName(), e.getMessage(), e);
        CscApiRequestParameter cscApiRequestParameter = e.getCscApiRequestParameter();
        if (cscApiRequestParameter == null) {
            CscApiErrorType error = CscApiErrorType.INVALID_REQUEST;
            String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
            return new CscApiErrorResponse(error, errorDescription);
        }
        String parameterName = cscApiRequestParameter.getParameterName();
        CscApiErrorType error = CscApiErrorType.INVALID_PARAMETER;
        String errorDescription = this.messageSource.getMessage(error.getApiError(), new String[]{parameterName}, LOCALE);
        return new CscApiErrorResponse(error, errorDescription);
    }

    @ExceptionHandler(CscCredentialDisabledException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CscApiErrorResponse handleCscCredentialDisabledException(CscCredentialDisabledException e) {
        LOGGER.error("Handling exception {}: {}", e.getClass().getSimpleName(), e.getMessage(), e);
        CscApiErrorType error = CscApiErrorType.CREDENTIAL_IS_DISABLED;
        String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
        return new CscApiErrorResponse(error, errorDescription);
    }

    @ExceptionHandler(CscInvalidParameterValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CscApiErrorResponse handleCscInvalidParameterValueException(CscInvalidParameterValueException e) {
        LOGGER.error("Handling exception {}: {}", e.getClass().getSimpleName(), e.getMessage(), e);
        CscApiRequestParameter cscApiRequestParameter = e.getCscApiRequestParameter();
        if (cscApiRequestParameter == null) {
            CscApiErrorType error = CscApiErrorType.INVALID_REQUEST;
            String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
            return new CscApiErrorResponse(error, errorDescription);
        }
        String parameterName = cscApiRequestParameter.getParameterName();
        CscApiErrorType error = CscApiErrorType.INVALID_PARAMETER_VALUE;
        String errorDescription = this.messageSource.getMessage(error.getApiError(), new String[]{parameterName}, LOCALE);
        return new CscApiErrorResponse(error, errorDescription);
    }

    @ExceptionHandler(CscNumberSignaturesTooHighException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CscApiErrorResponse handleCscNumberSignaturesTooHighException(CscNumberSignaturesTooHighException e) {
        LOGGER.error("Handling exception {}: {}", e.getClass().getSimpleName(), e.getMessage(), e);
        CscApiErrorType error = CscApiErrorType.NUM_SIGNATURES_TOO_HIGH;
        String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
        return new CscApiErrorResponse(error, errorDescription);
    }

    @ExceptionHandler(CscInvalidOtpException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CscApiErrorResponse handleCscInvalidOtpException(CscInvalidOtpException e) {
        LOGGER.error("Handling exception {}: {}", e.getClass().getSimpleName(), e.getMessage(), e);
        CscApiErrorType error = CscApiErrorType.INVALID_OTP;
        String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
        return new CscApiErrorResponse(error, errorDescription);
    }

    @ExceptionHandler(CscOtpLockedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CscApiErrorResponse handleCscOtpLockedException(CscOtpLockedException e) {
        LOGGER.error("Handling exception {}: {}", e.getClass().getSimpleName(), e.getMessage(), e);
        CscApiErrorType error = CscApiErrorType.LOCKED_OTP;
        String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
        return new CscApiErrorResponse(error, errorDescription);
    }

    @ExceptionHandler(CscInvalidPinException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CscApiErrorResponse handleCscInvalidPinException(CscInvalidPinException e) {
        LOGGER.error("Handling exception {}: {}", e.getClass().getSimpleName(), e.getMessage(), e);
        CscApiErrorType error = CscApiErrorType.INVALID_PIN;
        String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
        return new CscApiErrorResponse(error, errorDescription);
    }

    @ExceptionHandler(CscPinLockedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CscApiErrorResponse handleCscPinLockedException(CscPinLockedException e) {
        LOGGER.error("Handling exception {}: {}", e.getClass().getSimpleName(), e.getMessage(), e);
        CscApiErrorType error = CscApiErrorType.LOCKED_PIN;
        String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
        return new CscApiErrorResponse(error, errorDescription);
    }
}