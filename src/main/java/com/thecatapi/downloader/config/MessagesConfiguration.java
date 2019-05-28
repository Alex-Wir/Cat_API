package com.thecatapi.downloader.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

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
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("/applicationMessages.properties");
        return messageSource;
    }
}
