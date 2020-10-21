
package com.pvale.project.csc.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pvale.project.csc.api.enumerator.CredentialsInfoResponseCodeFormat;
import com.pvale.project.csc.api.enumerator.CredentialsInfoResponseCodePresence;
import com.pvale.project.csc.api.enumerator.CredentialsInfoResponseOtpType;

import java.util.Objects;

public class CredentialsInfoResponseOtp {

    private CredentialsInfoResponseCodePresence presence;
    private CredentialsInfoResponseOtpType type;
    private CredentialsInfoResponseCodeFormat format;
    private String label;
    private String description;
    @JsonProperty("ID")
    private String id;
    private String provider;

    public CredentialsInfoResponseOtp() {
        super();
    }

    public CredentialsInfoResponseOtp(CredentialsInfoResponseCodePresence presence, CredentialsInfoResponseOtpType type, CredentialsInfoResponseCodeFormat format, String label, String description, String id, String provider) {
        this.presence = presence;
        this.type = type;
        this.format = format;
        this.label = label;
        this.description = description;
        this.id = id;
        this.provider = provider;
    }

    public CredentialsInfoResponseCodePresence getPresence() {
        return presence;
    }

    public void setPresence(CredentialsInfoResponseCodePresence presence) {
        this.presence = presence;
    }

    public CredentialsInfoResponseOtpType getType() {
        return type;
    }

    public void setType(CredentialsInfoResponseOtpType type) {
        this.type = type;
    }

    public CredentialsInfoResponseCodeFormat getFormat() {
        return format;
    }

    public void setFormat(CredentialsInfoResponseCodeFormat format) {
        this.format = format;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CredentialsInfoResponseOtp)) return false;
        CredentialsInfoResponseOtp credentialsInfoResponseOtp = (CredentialsInfoResponseOtp) o;
        return getPresence() == credentialsInfoResponseOtp.getPresence() &&
                getType() == credentialsInfoResponseOtp.getType() &&
                getFormat() == credentialsInfoResponseOtp.getFormat() &&
                Objects.equals(getLabel(), credentialsInfoResponseOtp.getLabel()) &&
                Objects.equals(getDescription(), credentialsInfoResponseOtp.getDescription()) &&
                Objects.equals(getId(), credentialsInfoResponseOtp.getId()) &&
                Objects.equals(getProvider(), credentialsInfoResponseOtp.getProvider());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPresence(), getType(), getFormat(), getLabel(), getDescription(), getId(), getProvider());
    }

    @Override
    public String toString() {
        return "CscOtp{" +
                "presence=" + presence +
                ", type=" + type +
                ", format=" + format +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                ", provider='" + provider + '\'' +
                '}';
    }
}