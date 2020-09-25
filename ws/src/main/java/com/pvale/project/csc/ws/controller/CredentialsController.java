package com.pvale.project.csc.ws.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/credentials")
public class CredentialsController {

    private static final String CREDENTIALS_LIST_CONTEXT_PATH = "/list";
    private static final String CREDENTIALS_INFO_CONTEXT_PATH = "/info";
    private static final String CREDENTIALS_SEND_OTP_CONTEXT_PATH = "/sendOTP";
    private static final String CREDENTIALS_AUTHORIZE_CONTEXT_PATH = "/authorize";
    private static final String CREDENTIALS_EXTEND_TRANSACTION_CONTEXT_PATH = "/extendTransaction";

}