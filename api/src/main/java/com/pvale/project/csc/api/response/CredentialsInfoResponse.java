package com.pvale.project.csc.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pvale.project.csc.api.enumerator.CredentialsInfoResponseAuthMode;
import com.pvale.project.csc.api.enumerator.CredentialsInfoResponseScal;

import java.util.Objects;

public class CredentialsInfoResponse {

    private String description;
    private CredentialsInfoResponseKey key;
    private CredentialsInfoResponseCert cert;
    private CredentialsInfoResponseAuthMode authMode;
    @JsonProperty("SCAL")
    private CredentialsInfoResponseScal scal;
    @JsonProperty("PIN")
    private CredentialsInfoResponsePin pin;
    @JsonProperty("OTP")
    private CredentialsInfoResponseOtp otp;
    @JsonProperty("multisign")
    private Integer multiSign;
    private String lang;

    public CredentialsInfoResponse() {
        super();
    }

    public CredentialsInfoResponse(String description, CredentialsInfoResponseKey key, CredentialsInfoResponseCert cert, CredentialsInfoResponseAuthMode authMode, CredentialsInfoResponseScal scal, CredentialsInfoResponsePin pin, CredentialsInfoResponseOtp otp, Integer multiSign, String lang) {
        this.description = description;
        this.key = key;
        this.cert = cert;
        this.authMode = authMode;
        this.scal = scal;
        this.pin = pin;
        this.otp = otp;
        this.multiSign = multiSign;
        this.lang = lang;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CredentialsInfoResponseKey getKey() {
        return key;
    }

    public void setKey(CredentialsInfoResponseKey key) {
        this.key = key;
    }

    public CredentialsInfoResponseCert getCert() {
        return cert;
    }

    public void setCert(CredentialsInfoResponseCert cert) {
        this.cert = cert;
    }

    public CredentialsInfoResponseAuthMode getAuthMode() {
        return authMode;
    }

    public void setAuthMode(CredentialsInfoResponseAuthMode authMode) {
        this.authMode = authMode;
    }

    public CredentialsInfoResponseScal getScal() {
        return scal;
    }

    public void setScal(CredentialsInfoResponseScal scal) {
        this.scal = scal;
    }

    public CredentialsInfoResponsePin getPin() {
        return pin;
    }

    public void setPin(CredentialsInfoResponsePin pin) {
        this.pin = pin;
    }

    public CredentialsInfoResponseOtp getOtp() {
        return otp;
    }

    public void setOtp(CredentialsInfoResponseOtp otp) {
        this.otp = otp;
    }

    public Integer getMultiSign() {
        return multiSign;
    }

    public void setMultiSign(Integer multiSign) {
        this.multiSign = multiSign;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CredentialsInfoResponse)) return false;
        CredentialsInfoResponse that = (CredentialsInfoResponse) o;
        return Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getKey(), that.getKey()) &&
                Objects.equals(getCert(), that.getCert()) &&
                getAuthMode() == that.getAuthMode() &&
                getScal() == that.getScal() &&
                Objects.equals(getPin(), that.getPin()) &&
                Objects.equals(getOtp(), that.getOtp()) &&
                Objects.equals(getMultiSign(), that.getMultiSign()) &&
                Objects.equals(getLang(), that.getLang());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getKey(), getCert(), getAuthMode(), getScal(), getPin(), getOtp(), getMultiSign(), getLang());
    }

    @Override
    public String toString() {
        return "CredentialsInfoResponse{" +
                "description='" + description + '\'' +
                ", key=" + key +
                ", cert=" + cert +
                ", authMode=" + authMode +
                ", scal=" + scal +
                ", pin=" + pin +
                ", otp=" + otp +
                ", multiSign=" + multiSign +
                ", lang='" + lang + '\'' +
                '}';
    }
}