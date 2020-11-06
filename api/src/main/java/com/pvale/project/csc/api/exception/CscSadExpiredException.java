package com.pvale.project.csc.api.exception;

public class CscSadExpiredException extends CscApiException {

    private static final long serialVersionUID = -3982952562656898458L;

    public CscSadExpiredException() {
        super();
    }

    public CscSadExpiredException(String message) {
        super(message);
    }

    public CscSadExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public CscSadExpiredException(Throwable cause) {
        super(cause);
    }

    public CscSadExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}