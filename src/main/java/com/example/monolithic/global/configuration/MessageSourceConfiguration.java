package com.example.monolithic.global.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageSourceConfiguration {

    @Bean
    public MessageSource messageSource(@Value("${spring.messages.basename}") String[] names) {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(names);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
