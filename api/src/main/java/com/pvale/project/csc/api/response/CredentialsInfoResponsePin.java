package com.pvale.project.csc.api.response;

import com.pvale.project.csc.api.enumerator.CredentialsInfoResponseCodeFormat;
import com.pvale.project.csc.api.enumerator.CredentialsInfoResponseCodePresence;

import java.util.Objects;

public class CredentialsInfoResponsePin {

    private CredentialsInfoResponseCodePresence presence;
    private CredentialsInfoResponseCodeFormat format;
    private String label;
    private String description;

    public CredentialsInfoResponsePin() {
        super();
    }

    public CredentialsInfoResponsePin(CredentialsInfoResponseCodePresence presence, CredentialsInfoResponseCodeFormat format, String label, String description) {
        this.presence = presence;
        this.format = format;
        this.label = label;
        this.description = description;
    }

    public CredentialsInfoResponseCodePresence getPresence() {
        return presence;
    }

    public void setPresence(CredentialsInfoResponseCodePresence presence) {
        this.presence = presence;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CredentialsInfoResponsePin)) return false;
        CredentialsInfoResponsePin credentialsInfoResponsePin = (CredentialsInfoResponsePin) o;
        return getPresence() == credentialsInfoResponsePin.getPresence() &&
                getFormat() == credentialsInfoResponsePin.getFormat() &&
                Objects.equals(getLabel(), credentialsInfoResponsePin.getLabel()) &&
                Objects.equals(getDescription(), credentialsInfoResponsePin.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPresence(), getFormat(), getLabel(), getDescription());
    }

    @Override
    public String toString() {
        return "CscPin{" +
                "presence=" + presence +
                ", format=" + format +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}