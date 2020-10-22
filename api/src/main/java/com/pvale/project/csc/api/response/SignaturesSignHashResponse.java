package com.pvale.project.csc.api.response;

import java.util.Objects;
import java.util.Set;

public class SignaturesSignHashResponse {

    Set<String> signatures;

    public SignaturesSignHashResponse() {
        super();
    }

    public SignaturesSignHashResponse(Set<String> signatures) {
        this.signatures = signatures;
    }

    public Set<String> getSignatures() {
        return signatures;
    }

    public void setSignatures(Set<String> signatures) {
        this.signatures = signatures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SignaturesSignHashResponse)) return false;
        SignaturesSignHashResponse that = (SignaturesSignHashResponse) o;
        return Objects.equals(getSignatures(), that.getSignatures());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSignatures());
    }

    @Override
    public String toString() {
        return "SignaturesSignHashResponse{" +
                "signatures=" + signatures +
                '}';
    }
}