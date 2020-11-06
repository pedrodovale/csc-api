package com.pvale.project.csc.api.exception;

import com.pvale.project.csc.api.enumerator.CscApiRequestParameter;

public class CscInvalidParameterException extends CscApiException {

    private static final long serialVersionUID = 7878630048701415184L;

    private CscApiRequestParameter cscApiRequestParameter;

    public CscInvalidParameterException(CscApiRequestParameter cscApiRequestParameter) {
        this.cscApiRequestParameter = cscApiRequestParameter;
    }

    public CscInvalidParameterException(String message, CscApiRequestParameter cscApiRequestParameter) {
        super(message);
        this.cscApiRequestParameter = cscApiRequestParameter;
    }

    public CscInvalidParameterException(String message, Throwable cause, CscApiRequestParameter cscApiRequestParameter) {
        super(message, cause);
        this.cscApiRequestParameter = cscApiRequestParameter;
    }

    public CscInvalidParameterException(Throwable cause, CscApiRequestParameter cscApiRequestParameter) {
        super(cause);
        this.cscApiRequestParameter = cscApiRequestParameter;
    }

    public CscInvalidParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, CscApiRequestParameter cscApiRequestParameter) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.cscApiRequestParameter = cscApiRequestParameter;
    }

    public CscApiRequestParameter getCscApiRequestParameter() {
        return cscApiRequestParameter;
    }
}