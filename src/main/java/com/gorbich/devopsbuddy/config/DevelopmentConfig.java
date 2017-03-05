package com.gorbich.devopsbuddy.config;

import com.gorbich.devopsbuddy.backend.service.EmailService;
import com.gorbich.devopsbuddy.backend.service.MockEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("dev")
@PropertySource("file:///D:/STS/.devopsbuddy/application-dev.properties")
public class DevelopmentConfig {

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
