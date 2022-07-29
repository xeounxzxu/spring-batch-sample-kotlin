package io.example.dataserving.config

import io.example.dataserving.utils.JobParametersUtil
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
        "io.example.dataserving.repository"
    ]
)
@ComponentScan(
    basePackages = [
        "io.example.dataserving.repository",
        "io.example.dataserving.utils",
        "io.example.dataserving.job.incrementer",
    ]
)
@EntityScan(
    basePackages = [
        "io.example.dataserving.domain"
    ]
)
class TestBatchConfiguration {

    @Bean
    fun mockJobParameters(): JobParametersUtil = JobParametersUtil()
}
