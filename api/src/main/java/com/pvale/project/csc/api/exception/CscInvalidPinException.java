package com.pvale.project.csc.api.exception;

public class CscInvalidPinException extends CscApiException {

    private static final long serialVersionUID = 3271903444915244332L;

    public CscInvalidPinException() {
        super();
    }

    public CscInvalidPinException(String message) {
        super(message);
    }

    public CscInvalidPinException(String message, Throwable cause) {
        super(message, cause);
    }

    public CscInvalidPinException(Throwable cause) {
        super(cause);
    }

    public CscInvalidPinException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}