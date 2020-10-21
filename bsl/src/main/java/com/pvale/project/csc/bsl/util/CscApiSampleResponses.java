package com.pvale.project.csc.bsl.util;

import com.pvale.project.csc.api.enumerator.CredentialsInfoResponseAuthMode;
import com.pvale.project.csc.api.enumerator.CredentialsInfoResponseCertificateStatus;
import com.pvale.project.csc.api.enumerator.CredentialsInfoResponseCodeFormat;
import com.pvale.project.csc.api.enumerator.CredentialsInfoResponseCodePresence;
import com.pvale.project.csc.api.enumerator.CredentialsInfoResponseKeyStatus;
import com.pvale.project.csc.api.enumerator.CredentialsInfoResponseOtpType;
import com.pvale.project.csc.api.response.CredentialsInfoResponse;
import com.pvale.project.csc.api.response.CredentialsInfoResponseCert;
import com.pvale.project.csc.api.response.CredentialsInfoResponseKey;
import com.pvale.project.csc.api.response.CredentialsInfoResponseOtp;
import com.pvale.project.csc.api.response.CredentialsInfoResponsePin;
import com.pvale.project.csc.api.response.CredentialsListResponse;
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

    public static CredentialsListResponse credentialsList() {

        CredentialsListResponse credentialsListResponse = new CredentialsListResponse();

        credentialsListResponse.setCredentialIDs(Stream.of("GX0112348", "HX0224685").collect(Collectors.toSet()));

        return credentialsListResponse;
    }

    public static CredentialsInfoResponse credentialsInfo() {

        CredentialsInfoResponse credentialsInfoResponse = new CredentialsInfoResponse();

        CredentialsInfoResponseKey credentialsInfoResponseKey = new CredentialsInfoResponseKey();
        credentialsInfoResponseKey.setStatus(CredentialsInfoResponseKeyStatus.ENABLED);
        credentialsInfoResponseKey.setAlgo(Stream.of(
                "1.2.840.113549.1.1.1",
                "0.4.0.127.0.7.1.1.4.1.3").collect(Collectors.toSet()));
        credentialsInfoResponseKey.setLen(2048);
        credentialsInfoResponse.setKey(credentialsInfoResponseKey);

        CredentialsInfoResponseCert credentialsInfoResponseCert = new CredentialsInfoResponseCert();
        credentialsInfoResponseCert.setStatus(CredentialsInfoResponseCertificateStatus.VALID);
        credentialsInfoResponseCert.setCertificates(Stream.of(
                "<Base64-encoded_X.509_end_entity_certificate>",
                "<Base64-encoded_X.509_intermediate_CA_certificate>",
                "<Base64-encoded_X.509_root_CA_certificate>").collect(Collectors.toSet()));
        credentialsInfoResponseCert.setIssuerDn("<X.500_issuer_DN_printable_string>");
        credentialsInfoResponseCert.setSerialNumber("5AAC41CD8FA22B953640");
        credentialsInfoResponseCert.setSubjectDn("<X.500_subject_DN_printable_string>");
        credentialsInfoResponseCert.setValidFrom("20180101100000Z");
        credentialsInfoResponseCert.setValidTo("20190101095959Z");
        credentialsInfoResponse.setCert(credentialsInfoResponseCert);

        credentialsInfoResponse.setAuthMode(CredentialsInfoResponseAuthMode.EXPLICIT);

        CredentialsInfoResponsePin credentialsInfoResponsePin = new CredentialsInfoResponsePin();
        credentialsInfoResponsePin.setPresence(CredentialsInfoResponseCodePresence.TRUE);
        credentialsInfoResponsePin.setFormat(CredentialsInfoResponseCodeFormat.N);
        credentialsInfoResponsePin.setLabel("PIN");
        credentialsInfoResponsePin.setDescription("Please enter the signature PIN");
        credentialsInfoResponse.setPin(credentialsInfoResponsePin);

        CredentialsInfoResponseOtp credentialsInfoResponseOtp = new CredentialsInfoResponseOtp();
        credentialsInfoResponseOtp.setPresence(CredentialsInfoResponseCodePresence.TRUE);
        credentialsInfoResponseOtp.setType(CredentialsInfoResponseOtpType.OFFLINE);
        credentialsInfoResponseOtp.setId("MB01-K741200");
        credentialsInfoResponseOtp.setProvider("totp");
        credentialsInfoResponseOtp.setFormat(CredentialsInfoResponseCodeFormat.N);
        credentialsInfoResponseOtp.setLabel("Mobile OTP");
        credentialsInfoResponseOtp.setDescription("Please enter the 6 digit code you received by SMS");
        credentialsInfoResponse.setOtp(credentialsInfoResponseOtp);

        credentialsInfoResponse.setMultiSign(5);

        credentialsInfoResponse.setLang("en-US");

        return credentialsInfoResponse;
    }
}