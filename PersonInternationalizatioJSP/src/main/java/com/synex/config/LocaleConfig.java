package com.synex.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

@Configuration
public class LocaleConfig {

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.ENGLISH); // Set a default locale
        resolver.setCookieName("language"); // Name of the cookie to store the locale
        resolver.setCookieMaxAge(3600 * 24 * 30); // Cookie expiry time (in seconds)
        return resolver;
    }
}