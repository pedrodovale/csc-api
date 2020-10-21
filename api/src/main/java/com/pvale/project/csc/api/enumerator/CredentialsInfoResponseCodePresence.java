package com.pvale.project.csc.api.enumerator;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CredentialsInfoResponseCodePresence {

    @JsonProperty("true")
    TRUE,
    @JsonProperty("false")
    FALSE,
    @JsonProperty("optional")
    OPTIONAL;

}