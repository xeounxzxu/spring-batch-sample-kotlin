package io.example.dataserving.job

import org.noveltaaker.jlogger.JLogger
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SimpleJob2Configuration constructor(
    val jobBuilderFactory: JobBuilderFactory,
    val stepBuilderFactory: StepBuilderFactory
) : JLogger {

    @Bean
    fun simpleJob2() = jobBuilderFactory.get("simpleJob2")
        .start(simpleStep2(null))
        .build()

    @Bean
    @JobScope
    // Program arguments 에서 @Value 값을 넣어 줄 수 있다.
    fun simpleStep2(@Value("#{jobParameters[requestDate]}") requestDate: String?) =
        stepBuilderFactory.get("simpleStep2")
            .tasklet { contribution, chunkContext ->
                logger.info("====> simple step 2")
                logger.info("[requestDate]:$requestDate")
                RepeatStatus.FINISHED
            }
            .build()
}
