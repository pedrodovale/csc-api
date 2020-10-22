package com.pvale.project.csc.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class CredentialsSendOtpRequest {

    @NotNull
    @JsonProperty("credentialID")
    private String credentialId;
    private String clientData;

    public CredentialsSendOtpRequest() {
        super();
    }

    public CredentialsSendOtpRequest(@NotNull String credentialId, String clientData) {
        this.credentialId = credentialId;
        this.clientData = clientData;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public String getClientData() {
        return clientData;
    }

    public void setClientData(String clientData) {
        this.clientData = clientData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CredentialsSendOtpRequest)) return false;
        CredentialsSendOtpRequest that = (CredentialsSendOtpRequest) o;
        return Objects.equals(getCredentialId(), that.getCredentialId()) &&
                Objects.equals(getClientData(), that.getClientData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCredentialId(), getClientData());
    }

    @Override
    public String toString() {
        return "CredentialsSendOTPRequest{" +
                "credentialId='" + credentialId + '\'' +
                ", clientData='" + clientData + '\'' +
                '}';
    }
}