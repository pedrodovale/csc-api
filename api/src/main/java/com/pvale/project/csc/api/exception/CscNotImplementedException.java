package com.pvale.project.csc.api.exception;

public class CscNotImplementedException extends CscApiException {

    private static final long serialVersionUID = 8885445367426377819L;

    public CscNotImplementedException() {
        super();
    }

    public CscNotImplementedException(String message) {
        super(message);
    }

    public CscNotImplementedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CscNotImplementedException(Throwable cause) {
        super(cause);
    }

    public CscNotImplementedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}