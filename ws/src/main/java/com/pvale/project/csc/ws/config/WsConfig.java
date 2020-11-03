package com.pvale.project.csc.ws.config;

import com.pvale.project.csc.bsl.config.BslConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({WsCoreConfig.class, BslConfig.class})
public class WsConfig {

}