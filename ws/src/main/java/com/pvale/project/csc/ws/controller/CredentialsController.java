package com.pvale.project.csc.ws.controller;

import com.pvale.project.csc.api.exception.CscApiException;
import com.pvale.project.csc.api.request.CredentialsAuthorizeRequest;
import com.pvale.project.csc.api.request.CredentialsExtendTransactionRequest;
import com.pvale.project.csc.api.request.CredentialsInfoRequest;
import com.pvale.project.csc.api.request.CredentialsListRequest;
import com.pvale.project.csc.api.request.CredentialsSendOtpRequest;
import com.pvale.project.csc.api.response.CredentialsAuthorizeResponse;
import com.pvale.project.csc.api.response.CredentialsExtendTransactionResponse;
import com.pvale.project.csc.api.response.CredentialsInfoResponse;
import com.pvale.project.csc.api.response.CredentialsListResponse;
import com.pvale.project.csc.bsl.service.CscApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/credentials")
public class CredentialsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CredentialsController.class);

    public static final String CREDENTIALS_LIST_CONTEXT_PATH = "/list";
    static final String CREDENTIALS_INFO_CONTEXT_PATH = "/info";
    static final String CREDENTIALS_AUTHORIZE_CONTEXT_PATH = "/authorize";
    static final String CREDENTIALS_EXTEND_TRANSACTION_CONTEXT_PATH = "/extendTransaction";
    static final String CREDENTIALS_SEND_OTP_CONTEXT_PATH = "/sendOTP";

    private CscApiService cscApiService;

    @Autowired
    public CredentialsController(CscApiService cscApiService) {
        this.cscApiService = cscApiService;
    }

    @PostMapping(value = CREDENTIALS_LIST_CONTEXT_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CredentialsListResponse credentialsList(@Valid @RequestBody CredentialsListRequest credentialsListRequest) throws CscApiException {
        LOGGER.info("REST API request to method /credentials{}", CREDENTIALS_LIST_CONTEXT_PATH);
        return this.cscApiService.credentialsList(credentialsListRequest);
    }

    @PostMapping(value = CREDENTIALS_INFO_CONTEXT_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CredentialsInfoResponse credentialsInfo(@Valid @RequestBody CredentialsInfoRequest credentialsInfoRequest) throws CscApiException {
        LOGGER.info("REST API request to method /credentials{}", CREDENTIALS_INFO_CONTEXT_PATH);
        return this.cscApiService.credentialsInfo(credentialsInfoRequest);
    }

    @PostMapping(value = CREDENTIALS_AUTHORIZE_CONTEXT_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CredentialsAuthorizeResponse credentialsAuthorizeResponse(@Valid @RequestBody CredentialsAuthorizeRequest credentialsAuthorizeRequest) throws CscApiException {
        LOGGER.info("REST API request to method /credentials{}", CREDENTIALS_AUTHORIZE_CONTEXT_PATH);
        return this.cscApiService.credentialsAuthorize(credentialsAuthorizeRequest);
    }

    @PostMapping(value = CREDENTIALS_EXTEND_TRANSACTION_CONTEXT_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CredentialsExtendTransactionResponse credentialsExtendTransaction(@Valid @RequestBody CredentialsExtendTransactionRequest credentialsExtendTransactionRequest) throws CscApiException {
        LOGGER.info("REST API request to method /credentials{}", CREDENTIALS_EXTEND_TRANSACTION_CONTEXT_PATH);
        return this.cscApiService.credentialsExtendTransaction(credentialsExtendTransactionRequest);
    }

    @PostMapping(value = CREDENTIALS_SEND_OTP_CONTEXT_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void credentialsSendOtp(@Valid @RequestBody CredentialsSendOtpRequest credentialsSendOtpRequest) throws CscApiException {
        LOGGER.info("REST API request to method /credentials{}", CREDENTIALS_SEND_OTP_CONTEXT_PATH);
        this.cscApiService.credentialsSendOtp(credentialsSendOtpRequest);
    }
}