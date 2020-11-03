package com.pvale.project.csc.ws.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Configuration
public class WsCoreConfig {

    @Bean(name = "cscApiErrorMessages")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:csc-api-errors");
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
        messageSource.setDefaultLocale(Locale.ENGLISH);
        return messageSource;
    }
}