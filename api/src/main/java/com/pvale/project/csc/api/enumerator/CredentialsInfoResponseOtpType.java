package com.pvale.project.csc.api.enumerator;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CredentialsInfoResponseOtpType {

    @JsonProperty("offline")
    OFFLINE,
    @JsonProperty("online")
    ONLINE;

}