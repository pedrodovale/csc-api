package com.pvale.project.csc.api.exception;

public class CscNumberSignaturesTooHighException extends CscApiException {

    private static final long serialVersionUID = 3115586057790581997L;

    public CscNumberSignaturesTooHighException() {
        super();
    }

    public CscNumberSignaturesTooHighException(String message) {
        super(message);
    }

    public CscNumberSignaturesTooHighException(String message, Throwable cause) {
        super(message, cause);
    }

    public CscNumberSignaturesTooHighException(Throwable cause) {
        super(cause);
    }

    public CscNumberSignaturesTooHighException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}