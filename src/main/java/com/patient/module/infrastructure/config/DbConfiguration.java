package com.patient.module.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.patient.module.adoptor.out")
@EnableJpaAuditing(auditorAwareRef = "auditAware")
@EnableTransactionManagement
public class DbConfiguration {
    
}
