package com.pvale.project.csc.api.exception;

public class CscCredentialDisabledException extends CscApiException {

    private static final long serialVersionUID = 8183596467055619491L;

    public CscCredentialDisabledException() {
        super();
    }

    public CscCredentialDisabledException(String message) {
        super(message);
    }

    public CscCredentialDisabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public CscCredentialDisabledException(Throwable cause) {
        super(cause);
    }

    public CscCredentialDisabledException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}