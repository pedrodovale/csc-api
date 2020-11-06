package com.pvale.project.csc.api.exception;

public class CscPinLockedException extends CscApiException {

    private static final long serialVersionUID = 2417748974459118360L;

    public CscPinLockedException() {
        super();
    }

    public CscPinLockedException(String message) {
        super(message);
    }

    public CscPinLockedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CscPinLockedException(Throwable cause) {
        super(cause);
    }

    public CscPinLockedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}