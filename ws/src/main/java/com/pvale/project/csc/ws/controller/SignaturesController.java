package com.pvale.project.csc.ws.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/signatures")
public class SignaturesController {

    private static final String SIGNATURES_SIGN_HASH_CONTEXT_PATH = "/signHash";
    private static final String SIGNATURES_TIMESTAMP_CONTEXT_PATH = "/timestamp";

}