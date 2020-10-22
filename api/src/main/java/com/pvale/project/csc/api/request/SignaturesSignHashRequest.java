package com.pvale.project.csc.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

public class SignaturesSignHashRequest {

    @NotNull
    @JsonProperty("credentialID")
    private String credentialId;
    @NotNull
    @JsonProperty("SAD")
    private String sad;
    @NotNull
    private Set<String> hash;
    private String hashAlgo;
    @NotNull
    private String signAlgo;
    private String signAlgoParameters;
    private String clientData;

    public SignaturesSignHashRequest() {
        super();
    }

    public SignaturesSignHashRequest(@NotNull String credentialId, @NotNull String sad, @NotNull Set<String> hash, String hashAlgo, @NotNull String signAlgo, String signAlgoParameters, String clientData) {
        this.credentialId = credentialId;
        this.sad = sad;
        this.hash = hash;
        this.hashAlgo = hashAlgo;
        this.signAlgo = signAlgo;
        this.signAlgoParameters = signAlgoParameters;
        this.clientData = clientData;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public String getSad() {
        return sad;
    }

    public void setSad(String sad) {
        this.sad = sad;
    }

    public Set<String> getHash() {
        return hash;
    }

    public void setHash(Set<String> hash) {
        this.hash = hash;
    }

    public String getHashAlgo() {
        return hashAlgo;
    }

    public void setHashAlgo(String hashAlgo) {
        this.hashAlgo = hashAlgo;
    }

    public String getSignAlgo() {
        return signAlgo;
    }

    public void setSignAlgo(String signAlgo) {
        this.signAlgo = signAlgo;
    }

    public String getSignAlgoParameters() {
        return signAlgoParameters;
    }

    public void setSignAlgoParameters(String signAlgoParameters) {
        this.signAlgoParameters = signAlgoParameters;
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
        if (!(o instanceof SignaturesSignHashRequest)) return false;
        SignaturesSignHashRequest that = (SignaturesSignHashRequest) o;
        return Objects.equals(getCredentialId(), that.getCredentialId()) &&
                Objects.equals(getSad(), that.getSad()) &&
                Objects.equals(getHash(), that.getHash()) &&
                Objects.equals(getHashAlgo(), that.getHashAlgo()) &&
                Objects.equals(getSignAlgo(), that.getSignAlgo()) &&
                Objects.equals(getSignAlgoParameters(), that.getSignAlgoParameters()) &&
                Objects.equals(getClientData(), that.getClientData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCredentialId(), getSad(), getHash(), getHashAlgo(), getSignAlgo(), getSignAlgoParameters(), getClientData());
    }

    @Override
    public String toString() {
        return "SignaturesSignHashRequest{" +
                "credentialId='" + credentialId + '\'' +
                ", sad='" + sad + '\'' +
                ", hash=" + hash +
                ", hashAlgo='" + hashAlgo + '\'' +
                ", signAlgo='" + signAlgo + '\'' +
                ", signAlgoParameters='" + signAlgoParameters + '\'' +
                ", clientData='" + clientData + '\'' +
                '}';
    }
}