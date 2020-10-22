package com.pvale.project.csc.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CredentialsExtendTransactionResponse {

    @JsonProperty("SAD")
    private String sad;
    private Integer expiresIn;

    public CredentialsExtendTransactionResponse() {
        super();
    }

    public CredentialsExtendTransactionResponse(String sad, Integer expiresIn) {
        this.sad = sad;
        this.expiresIn = expiresIn;
    }

    public String getSad() {
        return sad;
    }

    public void setSad(String sad) {
        this.sad = sad;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CredentialsExtendTransactionResponse)) return false;
        CredentialsExtendTransactionResponse that = (CredentialsExtendTransactionResponse) o;
        return Objects.equals(getSad(), that.getSad()) &&
                Objects.equals(getExpiresIn(), that.getExpiresIn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSad(), getExpiresIn());
    }

    @Override
    public String toString() {
        return "CredentialsExtendTransactionResponse{" +
                "sad='" + sad + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}