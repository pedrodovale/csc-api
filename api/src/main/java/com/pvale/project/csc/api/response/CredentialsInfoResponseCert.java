package com.pvale.project.csc.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pvale.project.csc.api.enumerator.CredentialsInfoResponseCertificateStatus;

import java.util.Objects;
import java.util.Set;

public class CredentialsInfoResponseCert {

    private CredentialsInfoResponseCertificateStatus status;
    private Set<String> certificates;
    @JsonProperty("issuerDN")
    private String issuerDn;
    private String serialNumber;
    @JsonProperty("subjectDN")
    private String subjectDn;
    private String validFrom;
    private String validTo;

    public CredentialsInfoResponseCert() {
        super();
    }

    public CredentialsInfoResponseCert(CredentialsInfoResponseCertificateStatus status, Set<String> certificates, String issuerDn, String serialNumber, String subjectDn, String validFrom, String validTo) {
        this.status = status;
        this.certificates = certificates;
        this.issuerDn = issuerDn;
        this.serialNumber = serialNumber;
        this.subjectDn = subjectDn;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public CredentialsInfoResponseCertificateStatus getStatus() {
        return status;
    }

    public void setStatus(CredentialsInfoResponseCertificateStatus status) {
        this.status = status;
    }

    public Set<String> getCertificates() {
        return certificates;
    }

    public void setCertificates(Set<String> certificates) {
        this.certificates = certificates;
    }

    public String getIssuerDn() {
        return issuerDn;
    }

    public void setIssuerDn(String issuerDn) {
        this.issuerDn = issuerDn;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSubjectDn() {
        return subjectDn;
    }

    public void setSubjectDn(String subjectDn) {
        this.subjectDn = subjectDn;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CredentialsInfoResponseCert)) return false;
        CredentialsInfoResponseCert credentialsInfoResponseCert = (CredentialsInfoResponseCert) o;
        return getStatus() == credentialsInfoResponseCert.getStatus() &&
                Objects.equals(getCertificates(), credentialsInfoResponseCert.getCertificates()) &&
                Objects.equals(getIssuerDn(), credentialsInfoResponseCert.getIssuerDn()) &&
                Objects.equals(getSerialNumber(), credentialsInfoResponseCert.getSerialNumber()) &&
                Objects.equals(getSubjectDn(), credentialsInfoResponseCert.getSubjectDn()) &&
                Objects.equals(getValidFrom(), credentialsInfoResponseCert.getValidFrom()) &&
                Objects.equals(getValidTo(), credentialsInfoResponseCert.getValidTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getCertificates(), getIssuerDn(), getSerialNumber(), getSubjectDn(), getValidFrom(), getValidTo());
    }

    @Override
    public String toString() {
        return "CscCert{" +
                "status=" + status +
                ", certificates=" + certificates +
                ", issuerDN='" + issuerDn + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", subjectDN='" + subjectDn + '\'' +
                ", validFrom='" + validFrom + '\'' +
                ", validTo='" + validTo + '\'' +
                '}';
    }
}