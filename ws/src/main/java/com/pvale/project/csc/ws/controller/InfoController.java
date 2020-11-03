package com.pvale.project.csc.ws.controller;

import com.pvale.project.csc.api.exception.CscApiException;
import com.pvale.project.csc.api.request.InfoRequest;
import com.pvale.project.csc.api.response.InfoResponse;
import com.pvale.project.csc.bsl.service.CscApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfoController.class);

    public static final String ROOT_CONTEXT_PATH = "/";
    public static final String INFO_CONTEXT_PATH = "/info";

    private CscApiService cscApiService;

    @Autowired
    public InfoController(CscApiService cscApiService) {
        this.cscApiService = cscApiService;
    }

    @GetMapping(value = ROOT_CONTEXT_PATH)
    public InfoResponse home() throws CscApiException {
        return this.info(null);
    }

    @PostMapping(value = INFO_CONTEXT_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public InfoResponse info(@RequestBody InfoRequest infoRequest) throws CscApiException {
        LOGGER.info("REST API request to method {}", INFO_CONTEXT_PATH);
        return this.cscApiService.info(infoRequest);
    }
}