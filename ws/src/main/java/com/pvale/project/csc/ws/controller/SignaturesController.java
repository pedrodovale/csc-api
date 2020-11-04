package com.pvale.project.csc.ws.controller;

import com.pvale.project.csc.api.exception.CscApiException;
import com.pvale.project.csc.api.request.SignaturesSignHashRequest;
import com.pvale.project.csc.api.response.SignaturesSignHashResponse;
import com.pvale.project.csc.bsl.service.CscApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/signatures")
public class SignaturesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignaturesController.class);

    public static final String SIGNATURES_SIGN_HASH_CONTEXT_PATH = "/signHash";
    private static final String SIGNATURES_TIMESTAMP_CONTEXT_PATH = "/timestamp";

    private CscApiService cscApiService;

    @Autowired
    public SignaturesController(CscApiService cscApiService) {
        this.cscApiService = cscApiService;
    }

    @PostMapping(value = SIGNATURES_SIGN_HASH_CONTEXT_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SignaturesSignHashResponse signaturesSignHash(@Valid @RequestBody SignaturesSignHashRequest signaturesSignHashRequest) throws CscApiException {
        LOGGER.info("REST API request to method /signatures{}", SIGNATURES_SIGN_HASH_CONTEXT_PATH);
        return this.cscApiService.signaturesSignHash(signaturesSignHashRequest);
    }
}