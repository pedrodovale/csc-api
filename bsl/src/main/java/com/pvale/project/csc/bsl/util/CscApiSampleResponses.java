package com.pvale.project.csc.bsl.util;

import com.pvale.project.csc.api.response.InfoResponse;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class containing static methods that return responses given as example in the CSC API V1.0.4.0 specification
 */
public class CscApiSampleResponses {

    public static InfoResponse info() {

        InfoResponse infoResponse = new InfoResponse();

        infoResponse.setSpecs("1.0.4.0"); // this one actually differs from the example (specs=1.0.3). Probably a typo in the doc
        infoResponse.setName("ACME Trust Services");
        infoResponse.setLogo("https://service.domain.org/images/logo.png");
        infoResponse.setRegion("IT");
        infoResponse.setLang("en-US");
        infoResponse.setDescription("An efficient remote signature service");
        infoResponse.setAuthType(Stream.of("basic", "oauth2code").collect(Collectors.toSet()));
        infoResponse.setOauth2("https://www.domain.org/");
        infoResponse.setMethods(Stream.of(
                "auth/login",
                "auth/revoke",
                "credentials/list",
                "credentials/info",
                "credentials/authorize",
                "credentials/sendOTP",
                "signatures/signHash")
                .collect(Collectors.toSet()));

        return infoResponse;
    }
}