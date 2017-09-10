package com.toptechsol.ipc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.toptechsol.ipc.web.conversion.DateFormatter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("Messages");
		return messageSource;
	}

	/*
	 * Add formatter for class {@link
	 * java.util.Date} in addition to the one registered by default
	 */
	@Override
	public void addFormatters(final FormatterRegistry registry) {
		super.addFormatters(registry);
		registry.addFormatter(dateFormatter());
	}

	@Bean
	public DateFormatter dateFormatter() {
		return new DateFormatter();
	}
}