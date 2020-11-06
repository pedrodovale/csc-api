package com.pvale.project.csc.api.exception;

public class CscUnauthorizedHashException extends CscApiException {

    private static final long serialVersionUID = 6170094237203576134L;

    public CscUnauthorizedHashException() {
        super();
    }

    public CscUnauthorizedHashException(String message) {
        super(message);
    }

    public CscUnauthorizedHashException(String message, Throwable cause) {
        super(message, cause);
    }

    public CscUnauthorizedHashException(Throwable cause) {
        super(cause);
    }

    public CscUnauthorizedHashException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}