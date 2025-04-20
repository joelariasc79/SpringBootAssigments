package com.synex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import jakarta.servlet.http.HttpServletRequest; // Changed import
import jakarta.servlet.http.HttpServletResponse; // Changed import
import java.util.Locale;

@Controller
public class LanguageController {

    @Autowired
    private LocaleResolver localeResolver;

    @GetMapping("/changeLanguage")
    public String changeLanguage(@RequestParam("lang") String lang, HttpServletRequest request, HttpServletResponse response) {
        Locale newLocale;
        switch (lang.toLowerCase()) {
            case "english":
                newLocale = Locale.ENGLISH;
                break;
            case "french":
                newLocale = Locale.FRENCH;
                break;
            case "german":
                newLocale = Locale.GERMAN;
                break;
            default:
                newLocale = RequestContextUtils.getLocale((jakarta.servlet.http.HttpServletRequest) request); // Keep this as it's already Jakarta
                break;
        }

        if (localeResolver != null) {
            localeResolver.setLocale(request, response, newLocale);
        } else {
            System.err.println("Error: localeResolver is null after autowiring!");
        }

        // Redirect back to the previous page or a specific page
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/"); // Redirect to the previous page or home
    }
}