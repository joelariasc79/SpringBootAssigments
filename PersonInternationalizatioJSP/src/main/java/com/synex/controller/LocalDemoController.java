package com.synex.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("locale")
public class LocalDemoController {

	private final MessageSource messageSource;
	
	// constructor
	public LocalDemoController(MessageSource messageSource) {
		this.messageSource = messageSource;
	} 
	
}
