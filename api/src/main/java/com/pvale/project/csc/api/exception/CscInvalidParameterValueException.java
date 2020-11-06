package com.pvale.project.csc.api.exception;

import com.pvale.project.csc.api.enumerator.CscApiRequestParameter;

public class CscInvalidParameterValueException extends CscApiException {

    private static final long serialVersionUID = 1611189958118167352L;

    private CscApiRequestParameter cscApiRequestParameter;

    public CscInvalidParameterValueException(CscApiRequestParameter cscApiRequestParameter) {
        this.cscApiRequestParameter = cscApiRequestParameter;
    }

    public CscInvalidParameterValueException(String message, CscApiRequestParameter cscApiRequestParameter) {
        super(message);
        this.cscApiRequestParameter = cscApiRequestParameter;
    }

    public CscInvalidParameterValueException(String message, Throwable cause, CscApiRequestParameter cscApiRequestParameter) {
        super(message, cause);
        this.cscApiRequestParameter = cscApiRequestParameter;
    }

    public CscInvalidParameterValueException(Throwable cause, CscApiRequestParameter cscApiRequestParameter) {
        super(cause);
        this.cscApiRequestParameter = cscApiRequestParameter;
    }

    public CscInvalidParameterValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, CscApiRequestParameter cscApiRequestParameter) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.cscApiRequestParameter = cscApiRequestParameter;
    }

    public CscApiRequestParameter getCscApiRequestParameter() {
        return cscApiRequestParameter;
    }
}
