package com.pvale.project.csc.api.exception;

import com.pvale.project.csc.api.enumerator.CscApiRequestParameter;

public class CscInvalidBase64ParameterException extends CscApiException {

    private static final long serialVersionUID = 1872671797070016069L;

    private CscApiRequestParameter cscApiRequestParameter;

    public CscInvalidBase64ParameterException(CscApiRequestParameter cscApiRequestParameter) {
        this.cscApiRequestParameter = cscApiRequestParameter;
    }

    public CscInvalidBase64ParameterException(String message, CscApiRequestParameter cscApiRequestParameter) {
        super(message);
        this.cscApiRequestParameter = cscApiRequestParameter;
    }

    public CscInvalidBase64ParameterException(String message, Throwable cause, CscApiRequestParameter cscApiRequestParameter) {
        super(message, cause);
        this.cscApiRequestParameter = cscApiRequestParameter;
    }

    public CscInvalidBase64ParameterException(Throwable cause, CscApiRequestParameter cscApiRequestParameter) {
        super(cause);
        this.cscApiRequestParameter = cscApiRequestParameter;
    }

    public CscInvalidBase64ParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, CscApiRequestParameter cscApiRequestParameter) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.cscApiRequestParameter = cscApiRequestParameter;
    }

    public CscApiRequestParameter getCscApiRequestParameter() {
        return cscApiRequestParameter;
    }
}