package io.example.dataserving.job

import io.example.dataserving.utils.LogUtil
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StepNextJobConfiguration constructor(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    private val logUtil: LogUtil
) {

    @Bean
    fun job3() = jobBuilderFactory.get("job3")
        .start(jobStep31())
        .next(jobStep32())
        .next(jobStep33())
        .build()

    @Bean
    fun jobStep31() = stepBuilderFactory.get("jobStep3-1")
        .tasklet { contribution, chunkContext ->
            logUtil.getLogger().info("start to job step3 in 1")
            RepeatStatus.FINISHED
        }
        .build()

    @Bean
    fun jobStep32() = stepBuilderFactory.get("jobStep3-2")
        .tasklet { contribution, chunkContext ->
            logUtil.getLogger().info("start to job step3 in 2")
            RepeatStatus.FINISHED
        }
        .build()

    @Bean
    fun jobStep33() = stepBuilderFactory.get("jobStep3-3")
        .tasklet { contribution, chunkContext ->
            logUtil.getLogger().info("start to job step3 in 3")
            RepeatStatus.FINISHED
        }
        .build()
}
