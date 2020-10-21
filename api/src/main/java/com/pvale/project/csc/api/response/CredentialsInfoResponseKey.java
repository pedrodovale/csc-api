
package com.pvale.project.csc.api.response;

import com.pvale.project.csc.api.enumerator.CredentialsInfoResponseKeyStatus;

import java.util.Objects;
import java.util.Set;

public class CredentialsInfoResponseKey {

    private CredentialsInfoResponseKeyStatus status;
    private Set<String> algo;
    private Integer len;
    private String curve;

    public CredentialsInfoResponseKey() {
        super();
    }

    public CredentialsInfoResponseKey(CredentialsInfoResponseKeyStatus status, Set<String> algo, Integer len, String curve) {
        this.status = status;
        this.algo = algo;
        this.len = len;
        this.curve = curve;
    }

    public CredentialsInfoResponseKeyStatus getStatus() {
        return status;
    }

    public void setStatus(CredentialsInfoResponseKeyStatus status) {
        this.status = status;
    }

    public Set<String> getAlgo() {
        return algo;
    }

    public void setAlgo(Set<String> algo) {
        this.algo = algo;
    }

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }

    public String getCurve() {
        return curve;
    }

    public void setCurve(String curve) {
        this.curve = curve;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CredentialsInfoResponseKey)) return false;
        CredentialsInfoResponseKey credentialsInfoResponseKey = (CredentialsInfoResponseKey) o;
        return getStatus() == credentialsInfoResponseKey.getStatus() &&
                Objects.equals(getAlgo(), credentialsInfoResponseKey.getAlgo()) &&
                Objects.equals(getLen(), credentialsInfoResponseKey.getLen()) &&
                Objects.equals(getCurve(), credentialsInfoResponseKey.getCurve());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getAlgo(), getLen(), getCurve());
    }

    @Override
    public String toString() {
        return "CscKey{" +
                "status=" + status +
                ", algo=" + algo +
                ", len=" + len +
                ", curve='" + curve + '\'' +
                '}';
    }
}