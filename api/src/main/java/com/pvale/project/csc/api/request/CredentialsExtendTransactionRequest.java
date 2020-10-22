package com.pvale.project.csc.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class CredentialsExtendTransactionRequest {

    @NotNull
    @JsonProperty("credentialID")
    private String credentialId;
    private List<String> hash;
    @NotNull
    @JsonProperty("SAD")
    private String sad;
    private String clientData;

    public CredentialsExtendTransactionRequest() {
        super();
    }

    public CredentialsExtendTransactionRequest(@NotNull String credentialId, List<String> hash, @NotNull String sad, String clientData) {
        this.credentialId = credentialId;
        this.hash = hash;
        this.sad = sad;
        this.clientData = clientData;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public List<String> getHash() {
        return hash;
    }

    public void setHash(List<String> hash) {
        this.hash = hash;
    }

    public String getSad() {
        return sad;
    }

    public void setSad(String sad) {
        this.sad = sad;
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
        if (!(o instanceof CredentialsExtendTransactionRequest)) return false;
        CredentialsExtendTransactionRequest that = (CredentialsExtendTransactionRequest) o;
        return Objects.equals(getCredentialId(), that.getCredentialId()) &&
                Objects.equals(getHash(), that.getHash()) &&
                Objects.equals(getSad(), that.getSad()) &&
                Objects.equals(getClientData(), that.getClientData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCredentialId(), getHash(), getSad(), getClientData());
    }

    @Override
    public String toString() {
        return "CredentialsExtendTransactionRequest{" +
                "credentialId='" + credentialId + '\'' +
                ", hash=" + hash +
                ", sad='" + sad + '\'' +
                ", clientData='" + clientData + '\'' +
                '}';
    }
}