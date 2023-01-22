package com.example.batchapp.job

import com.example.batchapp.job.tasklet.SimpleJob1Task1
import com.example.batchapp.utils.LoggerUtil
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
open class SimpleJob1Configuration constructor(
    private val loggerUtil: LoggerUtil,
    private val transactionManager: PlatformTransactionManager
) {


    @Bean
    open fun runningJob(jobRepository: JobRepository) =
        JobBuilder("runJob1", jobRepository)
            .start(runStep1(jobRepository))
            .build()

    @Bean
    open fun runStep1(jobRepository: JobRepository) = StepBuilder("step1", jobRepository)
        .tasklet(simpleJob1Task1(), transactionManager)
        .build()

    @Bean
    open fun simpleJob1Task1() = SimpleJob1Task1(loggerUtil)

}
