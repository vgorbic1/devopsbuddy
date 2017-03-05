package com.gorbich.devopsbuddy.config;

import com.gorbich.devopsbuddy.backend.service.EmailService;
import com.gorbich.devopsbuddy.backend.service.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("prod")
@PropertySource("file:///D:/STS/.devopsbuddy/application-prod.properties")
public class ProductionConfig {

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}