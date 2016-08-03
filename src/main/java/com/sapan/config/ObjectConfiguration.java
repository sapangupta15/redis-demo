package com.sapan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@ComponentScan("com.sapan")
public class ObjectConfiguration {
	
	@Bean(name= "studentMapper")
	public ObjectMapper studentMapper() {
		ObjectMapper mapper = new ObjectMapper();
		return mapper;
	}

}
