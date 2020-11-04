package com.pvale.project.csc.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Null;

public class CredentialsListRequest {

    @Null
    @JsonProperty("userID")
    private String userId; // for authentication parameters
    private Integer maxResults;
    private String pageToken;
    private String clientData;

    public CredentialsListRequest() {
        super();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    public String getPageToken() {
        return pageToken;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    public String getClientData() {
        return clientData;
    }

    public void setClientData(String clientData) {
        this.clientData = clientData;
    }
}
