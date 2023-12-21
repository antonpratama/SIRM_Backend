package com.anton.sirm.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.ZonedDateTime;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "userProvider", dateTimeProviderRef = "dateTimeProvider")
@SuppressWarnings("unused")
public class JpaConfig {
    private final PlatformTransactionManager transactionManager;

    public JpaConfig(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Bean
    @ConditionalOnMissingBean(TransactionTemplate.class)
    public TransactionTemplate transactionTemplate(){
        return new TransactionTemplate(transactionManager);
    }

    @Bean
    @ConditionalOnMissingBean(AuditorAware.class)
    public AuditorAware<String> userProvider(){
        return () -> Optional.of("SYSTEM");
    }

    @Bean
    @ConditionalOnMissingBean(DateTimeProvider.class)
    public DateTimeProvider dateTimeProvider(){
        return () -> Optional.of(ZonedDateTime.now());
    }
}
