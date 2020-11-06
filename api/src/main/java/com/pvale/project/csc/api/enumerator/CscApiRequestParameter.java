package com.pvale.project.csc.api.enumerator;

public enum CscApiRequestParameter {

    USER_ID("userID", "string"),
    CREDENTIAL_ID("credentialID", "string"),
    NUM_SIGNATURES("numSignatures", "integer"),
    HASH("hash", "array");

    String parameterName;
    String parameterType;

    CscApiRequestParameter(String parameterName, String parameterType) {
        this.parameterName = parameterName;
        this.parameterType = parameterType;
    }

    public String getParameterName() {
        return parameterName;
    }

    public String getParameterType() {
        return parameterType;
    }
}
