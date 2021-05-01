package com.barclays.ticketsystem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConfigurationProperties(prefix = "webconfig")
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	
    private String allowedorigins;

	public void setAllowedorigins(String allowedorigins) {
		this.allowedorigins = allowedorigins;
	}

	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins(allowedorigins);
    }
}