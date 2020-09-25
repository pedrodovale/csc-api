package com.pvale.project.csc.ws.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.pvale.project.csc.ws.controller"
})
public class WsMainConfig {
}