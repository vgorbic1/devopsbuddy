package com.gorbich.devopsbuddy.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.gorbich.devopsbuddy.backend.persistence.repositories")
@EntityScan(basePackages = "com.gorbich.devopsbuddy.backend.persistence.domain.backend")
@EnableTransactionManagement
public class ApplicationConfig {

}
