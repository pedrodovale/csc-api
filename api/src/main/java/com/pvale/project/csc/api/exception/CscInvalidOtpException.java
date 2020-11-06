package com.pvale.project.csc.api.exception;

public class CscInvalidOtpException extends CscApiException {

    private static final long serialVersionUID = 3271903444915244332L;

    public CscInvalidOtpException() {
        super();
    }

    public CscInvalidOtpException(String message) {
        super(message);
    }

    public CscInvalidOtpException(String message, Throwable cause) {
        super(message, cause);
    }

    public CscInvalidOtpException(Throwable cause) {
        super(cause);
    }

    public CscInvalidOtpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}