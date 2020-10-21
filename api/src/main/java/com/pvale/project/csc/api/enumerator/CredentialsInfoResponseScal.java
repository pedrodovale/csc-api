package com.pvale.project.csc.api.enumerator;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CredentialsInfoResponseScal {

    @JsonProperty("1")
    ONE("1"),
    @JsonProperty("2")
    TWO("2");

    private String serializedValue;

    CredentialsInfoResponseScal(String serializedValue) {
        this.serializedValue = serializedValue;
    }

}