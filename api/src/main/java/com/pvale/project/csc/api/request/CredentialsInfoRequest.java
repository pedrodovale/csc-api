package com.pvale.project.csc.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pvale.project.csc.api.enumerator.CredentialsInfoRequestCertificateType;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class CredentialsInfoRequest {

    @NotNull
    @JsonProperty("credentialID")
    private String credentialId;
    private CredentialsInfoRequestCertificateType certificates;
    private Boolean certInfo;
    private Boolean authInfo;
    private String lang;
    private String clientData;

    public CredentialsInfoRequest() {
        super();
    }

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public CredentialsInfoRequestCertificateType getCertificates() {
        return certificates;
    }

    public void setCertificates(CredentialsInfoRequestCertificateType certificates) {
        this.certificates = certificates;
    }

    public Boolean getCertInfo() {
        return certInfo;
    }

    public void setCertInfo(Boolean certInfo) {
        this.certInfo = certInfo;
    }

    public Boolean getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(Boolean authInfo) {
        this.authInfo = authInfo;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
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
        if (!(o instanceof CredentialsInfoRequest)) return false;
        CredentialsInfoRequest that = (CredentialsInfoRequest) o;
        return Objects.equals(getCredentialId(), that.getCredentialId()) &&
                getCertificates() == that.getCertificates() &&
                Objects.equals(getCertInfo(), that.getCertInfo()) &&
                Objects.equals(getAuthInfo(), that.getAuthInfo()) &&
                Objects.equals(getLang(), that.getLang()) &&
                Objects.equals(getClientData(), that.getClientData());
    }

    @Override
    public String toString() {
        return "CredentialsInfoRequest{" +
                "credentialID='" + credentialId + '\'' +
                ", certificates=" + certificates +
                ", certInfo=" + certInfo +
                ", authInfo=" + authInfo +
                ", lang='" + lang + '\'' +
                ", clientData='" + clientData + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCredentialId(), getCertificates(), getCertInfo(), getAuthInfo(), getLang(), getClientData());
    }
}