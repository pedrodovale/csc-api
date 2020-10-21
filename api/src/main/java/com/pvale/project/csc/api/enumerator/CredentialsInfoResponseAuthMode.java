package com.pvale.project.csc.api.enumerator;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CredentialsInfoResponseAuthMode {

    @JsonProperty("implicit")
    IMPLICIT,
    @JsonProperty("explicit")
    EXPLICIT,
    @JsonProperty("oauth2code")
    OAUTH2_CODE;

}
