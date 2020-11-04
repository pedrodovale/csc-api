package com.pvale.project.csc.api.enumerator;

public enum CscApiRequestParameter {

    USER_ID("userID"),
    CREDENTIAL_ID("credentialID"),
    NUM_SIGNATURES("numSignatures"),
    HASH("hash");

    String parameterName;

    CscApiRequestParameter(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return parameterName;
    }
}
