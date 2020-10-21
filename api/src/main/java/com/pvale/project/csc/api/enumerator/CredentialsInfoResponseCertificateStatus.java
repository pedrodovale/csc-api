package com.pvale.project.csc.api.enumerator;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CredentialsInfoResponseCertificateStatus {

    @JsonProperty("valid")
    VALID,
    @JsonProperty("expired")
    EXPIRED,
    @JsonProperty("revoked")
    REVOKED,
    @JsonProperty("suspended")
    SUSPENDED;

}