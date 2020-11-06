package com.pvale.project.csc.api.exception;

public class CscOtpLockedException extends CscApiException {

    private static final long serialVersionUID = 5163194208300500000L;

    public CscOtpLockedException() {
        super();
    }

    public CscOtpLockedException(String message) {
        super(message);
    }

    public CscOtpLockedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CscOtpLockedException(Throwable cause) {
        super(cause);
    }

    public CscOtpLockedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}