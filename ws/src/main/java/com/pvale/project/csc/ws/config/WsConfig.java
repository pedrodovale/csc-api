package com.pvale.project.csc.ws.config;

import com.pvale.project.csc.bsl.config.BslConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BslConfig.class)
@ComponentScan(basePackages = {
        "com.pvale.project.csc.ws.controller"
})
public class WsConfig {
}