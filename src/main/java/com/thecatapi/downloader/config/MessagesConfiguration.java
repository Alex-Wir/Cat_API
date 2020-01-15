package com.thecatapi.downloader.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * MessageSource Bean configuration class
 */
public class MessagesConfiguration {

    /**
     * Bean MessageSource configuration
     *
     * @return - MessageSource instance
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:applicationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
