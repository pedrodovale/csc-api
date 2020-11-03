package com.pvale.project.csc.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pvale.project.csc.api.enumerator.CscApiErrorType;

import java.util.Objects;

public class CscApiErrorResponse {

    private CscApiErrorType error;

    @JsonProperty("error_description")
    private String errorDescription;

    public CscApiErrorResponse() {
        super();
    }

    public CscApiErrorResponse(CscApiErrorType error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public CscApiErrorType getError() {
        return error;
    }

    public void setError(CscApiErrorType error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CscApiErrorResponse that = (CscApiErrorResponse) o;
        return Objects.equals(error, that.error) &&
                Objects.equals(errorDescription, that.errorDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(error, errorDescription);
    }

    @Override
    public String toString() {
        return "CscApiErrorResponse{" +
                "error=" + error +
                ", errorDescription='" + errorDescription + '\'' +
                '}';
    }
}
