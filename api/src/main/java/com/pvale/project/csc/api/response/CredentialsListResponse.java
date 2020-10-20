package com.pvale.project.csc.api.response;

import java.util.Set;

public class CredentialsListResponse {

    private Set<String> credentialIDs;
    private String nextPageToken;

    public CredentialsListResponse() {
        super();
    }

    public Set<String> getCredentialIDs() {
        return credentialIDs;
    }

    public void setCredentialIDs(Set<String> credentialIDs) {
        this.credentialIDs = credentialIDs;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }
}
