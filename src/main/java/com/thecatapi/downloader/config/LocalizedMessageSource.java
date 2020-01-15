package com.thecatapi.downloader.config;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Class for handling messages localization
 */
@Component
public class LocalizedMessageSource {

    private List<Locale> localeList = Arrays.asList(new Locale("ru"), new Locale("en"));

    private MessageSource messageSource;

    LocalizedMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Return message depending on the locale
     *
     * @param messageCode - message code
     * @param arguments   - unused peace of shit
     * @return - String Message
     */
    public String getMessage(String messageCode, Object[] arguments) {
        Locale locale = LocaleContextHolder.getLocale();
        locale = localeList.contains(locale) ? locale : Locale.getDefault();
        return messageSource.getMessage(messageCode, arguments, locale);
    }
}