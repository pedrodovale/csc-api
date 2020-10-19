package com.pvale.project.csc.api.exception;

public class CscApiException extends Exception {

    private static final long serialVersionUID = -4161688562815499589L;

    public CscApiException() {
    }

    public CscApiException(String message) {
        super(message);
    }

    public CscApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public CscApiException(Throwable cause) {
        super(cause);
    }

    public CscApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}