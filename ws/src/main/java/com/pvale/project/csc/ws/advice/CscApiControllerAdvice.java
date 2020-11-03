package com.pvale.project.csc.ws.advice;

import com.pvale.project.csc.api.enumerator.CscApiErrorType;
import com.pvale.project.csc.api.exception.CscServerErrorException;
import com.pvale.project.csc.api.response.CscApiErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice(basePackages = "com.pvale.project.csc.ws.controller")
public class CscApiControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(CscApiControllerAdvice.class);
    private static final Locale LOCALE = Locale.ENGLISH;

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
}