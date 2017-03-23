package com.gorbich.devopsbuddy.config;

import com.gorbich.devopsbuddy.backend.service.EmailService;
import com.gorbich.devopsbuddy.backend.service.MockEmailService;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("dev")
@PropertySource("file:///D:/STS/.devopsbuddy/application-dev.properties")
public class DevelopmentConfig {

	@Value("${stripe.test.private.key}")
	private String stripeDevKey;
	
    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
    
    @Bean
    public ServletRegistrationBean h2ConsoleServletRegistration() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new WebServlet());
        bean.addUrlMappings("/console/*");
        return bean;
    }
    
    @Bean
    public String stripeKey() {
    	return stripeDevKey;
    }
}
