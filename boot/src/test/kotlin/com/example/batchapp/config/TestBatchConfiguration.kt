package com.example.batchapp.config

import com.example.batchapp.utils.JobParametersUtil
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
@EnableJpaRepositories(
    basePackages = [
        "com.example.batchapp.repository"
    ]
)
@ComponentScan(
    basePackages = [
        "com.example.batchapp.repository",
        "com.example.batchapp.utils",
        "com.example.batchapp.job.incrementer",
    ]
)
@EntityScan(
    basePackages = [
        "com.example.batchapp.domain"
    ]
)
class TestBatchConfiguration {

    @Bean
    fun mockJobParameters(): JobParametersUtil = JobParametersUtil()
}
