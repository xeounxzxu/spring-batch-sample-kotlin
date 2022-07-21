package io.example.dataserving.job

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// @Configuration
class SimpleJob4Configuration constructor(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory
) {

    // @Bean
    // fun simpleJob4() = jobBuilderFactory.get("simpleJob4")
    //     .start(simpleJob4Step1())
    //     .build()
    //
    // @Bean
    // fun simpleJob4Step1() = stepBuilderFactory
    //     .get("simpleJob4Step1")
    //     .chunk<>()
    //     .build()
}