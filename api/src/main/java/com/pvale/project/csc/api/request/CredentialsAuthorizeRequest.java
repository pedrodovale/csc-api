package com.pvale.project.csc.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

public class CredentialsAuthorizeRequest {

    @NotNull
    @JsonProperty("credentialID")
    private String credentialId;
    @NotNull
    private Integer numSignatures;
    private Set<String> hash;
    @JsonProperty("PIN")
    private String pin;
    @JsonProperty("OTP")
    private String otp;
    private String description;
    private String clientData;

    public CredentialsAuthorizeRequest() {
        super();
    }

    public CredentialsAuthorizeRequest(@NotNull String credentialId, @NotNull Integer numSignatures, Set<String> hash, String pin, String otp, String description, String clientData) {
        this.credentialId = credentialId;
        this.numSignatures = numSignatures;
        this.hash = hash;
        this.pin = pin;
        this.otp = otp;
        this.description = description;
        this.clientData = clientData;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public Integer getNumSignatures() {
        return numSignatures;
    }

    public void setNumSignatures(Integer numSignatures) {
        this.numSignatures = numSignatures;
    }

    public Set<String> getHash() {
        return hash;
    }

    public void setHash(Set<String> hash) {
        this.hash = hash;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(o instanceof CredentialsAuthorizeRequest)) return false;
        CredentialsAuthorizeRequest that = (CredentialsAuthorizeRequest) o;
        return Objects.equals(getCredentialId(), that.getCredentialId()) &&
                Objects.equals(getNumSignatures(), that.getNumSignatures()) &&
                Objects.equals(getHash(), that.getHash()) &&
                Objects.equals(getPin(), that.getPin()) &&
                Objects.equals(getOtp(), that.getOtp()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getClientData(), that.getClientData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCredentialId(), getNumSignatures(), getHash(), getPin(), getOtp(), getDescription(), getClientData());
    }

    @Override
    public String toString() {
        return "CredentialsAuthorizeRequest{" +
                "credentialId='" + credentialId + '\'' +
                ", numSignatures=" + numSignatures +
                ", hash=" + hash +
                ", pin='" + pin + '\'' +
                ", otp='" + otp + '\'' +
                ", description='" + description + '\'' +
                ", clientData='" + clientData + '\'' +
                '}';
    }
}