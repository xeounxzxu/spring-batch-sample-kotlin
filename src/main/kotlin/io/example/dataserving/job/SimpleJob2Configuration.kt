package io.example.dataserving.job

import io.example.dataserving.job.incrementer.DateIncrementer
import io.example.dataserving.utils.LogUtil
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SimpleJob2Configuration constructor(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    private val logUtil: LogUtil,
    private val dateIncrementer: DateIncrementer
) {

    @Bean
    fun simpleJob2() = jobBuilderFactory.get("simpleJob2")
        .start(simpleStep2(null))
        .incrementer(dateIncrementer)
        .build()

    @Bean
    @JobScope
    // Program arguments 에서 @Value 값을 넣어 줄 수 있다.
    fun simpleStep2(@Value("#{jobParameters[requestDate]}") requestDate: String?) =
        stepBuilderFactory.get("simpleStep2")
            .tasklet { contribution, chunkContext ->
                logUtil.getLogger().info("====> simple step 2")
                logUtil.getLogger().info("[requestDate]:$requestDate")
                RepeatStatus.FINISHED
            }
            .build()
}
