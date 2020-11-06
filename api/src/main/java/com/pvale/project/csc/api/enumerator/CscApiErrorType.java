package com.pvale.project.csc.api.enumerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Field;

/**
 * When changing annotations, make sure @see CscApiErrorType.getApiError works afterwards
 */
public enum CscApiErrorType {

    // Error related to CSC API OAuth
    @JsonProperty("invalid_request")
    INVALID_REQUEST,
    @JsonProperty("unauthorized_client")
    UNAUTHORIZED_CLIENT,
    @JsonProperty("access_denied")
    ACCESS_DENIED,
    @JsonProperty("unsupported_response_type")
    UNSUPPORTED_RESPONSE_TYPE,
    @JsonProperty("invalid_scope")
    INVALID_SCOPE,
    @JsonProperty("authorization_server_error")
    AUTHORIZATION_SERVER_ERROR,
    @JsonProperty("temporarily_unavailable")
    TEMPORARILY_UNAVAILABLE,
    @JsonProperty("expired_token")
    EXPIRED_TOKEN,
    @JsonProperty("invalid_token")
    INVALID_TOKEN,

    // Errors related to CSC API usage
    @JsonProperty("server_error")
    SERVER_ERROR,
    @JsonProperty("parameter_null")
    PARAMETER_NULL,
    @JsonProperty("missing_or_invalid_type")
    MISSING_OR_INVALID_TYPE,
    @JsonProperty("invalid_parameter")
    INVALID_PARAMETER,
    @JsonProperty("credential_is_disabled")
    CREDENTIAL_IS_DISABLED,
    @JsonProperty("invalid_parameter_value")
    INVALID_PARAMETER_VALUE,
    @JsonProperty("num_signatures_too_high")
    NUM_SIGNATURES_TOO_HIGH,
    @JsonProperty("invalid_otp")
    INVALID_OTP,
    @JsonProperty("locked_otp")
    LOCKED_OTP,
    @JsonProperty("invalid_pin")
    INVALID_PIN,
    @JsonProperty("locked_pin")
    LOCKED_PIN,
    @JsonProperty("invalid_base64_parameter")
    INVALID_BASE64_PARAMETER,
    @JsonProperty("unauthorized_hash")
    UNAUTHORIZED_HASH,
    @JsonProperty("sad_expired")
    SAD_EXPIRED,
    @JsonProperty("invalid_digest_value_length")
    INVALID_DIGEST_VALUE_LENGTH,
    @JsonProperty("expired_certificate")
    EXPIRED_CERTIFICATE,
    @JsonProperty("revoked_certificate")
    REVOKED_CERTIFICATE,
    @JsonProperty("suspended_certificate")
    SUSPENDED_CERTIFICATE,
    @JsonProperty("empty_hash_array")
    EMPTY_HASH_ARRAY,
    @JsonProperty("not_implemented")
    NOT_IMPLEMENTED;

    public String getApiError() {
        try {
            Field field = this.getClass().getField(this.name());
            JsonProperty jsonProperty = (JsonProperty) field.getAnnotations()[0];
            return jsonProperty.value();
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException("Error getting API error string");
        }
    }
}