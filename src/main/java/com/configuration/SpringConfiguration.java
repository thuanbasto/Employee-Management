package com.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {
	
	/**
	 * Model mapper.
	 *
	 * @return the model mapper
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	/*
	 * @Bean public CommonsMultipartResolver multipartResolver() {
	 * CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	 * return multipartResolver; }
	 */
}
