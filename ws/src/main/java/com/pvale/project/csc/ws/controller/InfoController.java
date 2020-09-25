package com.pvale.project.csc.ws.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    private static final String ROOT_CONTEXT_PATH = "/";
    private static final String INFO_CONTEXT_PATH = "/info";

    @GetMapping(value = "/")
    public String home() {
        return this.info();
    }

    @GetMapping(value = INFO_CONTEXT_PATH)
    public String info() {
        return "info";
    }
}