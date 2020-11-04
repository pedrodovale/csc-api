package com.pvale.project.csc.ws.advice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pvale.project.csc.api.enumerator.CscApiErrorType;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.Collection;
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
    @ResponseBody
    public CscApiErrorResponse handleException(Exception e) {
        LOGGER.error("Handling exception {}: {}", e.getClass().getSimpleName(), e.getMessage(), e);
        CscApiErrorType error = CscApiErrorType.SERVER_ERROR;
        String errorDescription = this.messageSource.getMessage(error.getApiError(), null, LOCALE);
        return new CscApiErrorResponse(error, errorDescription);
    }

    /**
     * This method will try to determine the method parameter with error.
     * If it fails to do so, it'll just return the generic invalid request error
     *
     * @param e -> the input validation exception
     * @return the error response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
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
}