package com.pvale.project.csc.api.exception;

public class CscEmptyHashArrayException extends CscApiException {

    private static final long serialVersionUID = 6383332504284535571L;

    public CscEmptyHashArrayException() {
        super();
    }

    public CscEmptyHashArrayException(String message) {
        super(message);
    }

    public CscEmptyHashArrayException(String message, Throwable cause) {
        super(message, cause);
    }

    public CscEmptyHashArrayException(Throwable cause) {
        super(cause);
    }

    public CscEmptyHashArrayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}