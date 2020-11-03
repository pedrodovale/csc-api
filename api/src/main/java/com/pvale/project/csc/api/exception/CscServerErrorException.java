package com.pvale.project.csc.api.exception;

public class CscServerErrorException extends CscApiException {

    private static final long serialVersionUID = -6744512080550105732L;

    public CscServerErrorException() {
        super();
    }

    public CscServerErrorException(String message) {
        super(message);
    }

    public CscServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public CscServerErrorException(Throwable cause) {
        super(cause);
    }

    public CscServerErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}