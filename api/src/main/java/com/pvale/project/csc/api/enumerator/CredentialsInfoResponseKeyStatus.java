package com.pvale.project.csc.api.enumerator;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CredentialsInfoResponseKeyStatus {

    @JsonProperty("enabled")
    ENABLED,
    @JsonProperty("disabled")
    DISABLED;

}
