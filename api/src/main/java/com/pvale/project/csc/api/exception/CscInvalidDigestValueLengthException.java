package com.pvale.project.csc.api.exception;

public class CscInvalidDigestValueLengthException extends CscApiException {

    private static final long serialVersionUID = 277595165349932830L;

    public CscInvalidDigestValueLengthException() {
        super();
    }

    public CscInvalidDigestValueLengthException(String message) {
        super(message);
    }

    public CscInvalidDigestValueLengthException(String message, Throwable cause) {
        super(message, cause);
    }

    public CscInvalidDigestValueLengthException(Throwable cause) {
        super(cause);
    }

    public CscInvalidDigestValueLengthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}