package io.example.dataserving.config

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
@EnableJpaRepositories(
    basePackages = [
        "io.example.dataserving.repository"
    ]
)
@ComponentScan(
    basePackages = [
        "io.example.dataserving.repository"
    ]
)
@EntityScan(
    basePackages = [
        "io.example.dataserving.domain"
    ]
)
class TestBatchLegacyConfiguration
