package com.pvale.project.csc.ws.controller;

import com.pvale.project.csc.api.exception.CscApiException;
import com.pvale.project.csc.api.request.CredentialsInfoRequest;
import com.pvale.project.csc.api.request.CredentialsListRequest;
import com.pvale.project.csc.api.response.CredentialsInfoResponse;
import com.pvale.project.csc.api.response.CredentialsListResponse;
import com.pvale.project.csc.bsl.service.CscApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/credentials")
public class CredentialsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CredentialsController.class);

    public static final String CREDENTIALS_LIST_CONTEXT_PATH = "/list";
    public static final String CREDENTIALS_INFO_CONTEXT_PATH = "/info";
    private static final String CREDENTIALS_SEND_OTP_CONTEXT_PATH = "/sendOTP";
    private static final String CREDENTIALS_AUTHORIZE_CONTEXT_PATH = "/authorize";
    private static final String CREDENTIALS_EXTEND_TRANSACTION_CONTEXT_PATH = "/extendTransaction";

    private CscApiService cscApiService;

    @Autowired
    public CredentialsController(CscApiService cscApiService) {
        this.cscApiService = cscApiService;
    }

    @PostMapping(value = CREDENTIALS_LIST_CONTEXT_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CredentialsListResponse credentialsList(CredentialsListRequest credentialsListRequest) throws CscApiException {
        LOGGER.info("REST API request to method {}", CREDENTIALS_LIST_CONTEXT_PATH);
        return this.cscApiService.credentialsList(credentialsListRequest);
    }

    @PostMapping(value = CREDENTIALS_INFO_CONTEXT_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CredentialsInfoResponse credentialsInfo(CredentialsInfoRequest credentialsInfoRequest) throws CscApiException {
        LOGGER.info("REST API request to method {}", CREDENTIALS_INFO_CONTEXT_PATH);
        return this.cscApiService.credentialsInfo(credentialsInfoRequest);
    }
}