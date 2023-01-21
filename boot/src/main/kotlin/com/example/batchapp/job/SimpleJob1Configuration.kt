package com.example.batchapp.job

import com.example.batchapp.job.listener.SimpleJob1Listener
import com.example.batchapp.job.listener.SimpleJob1OtherListener
import com.example.batchapp.job.tasklet.SimpleJob1Task1
import com.example.batchapp.utils.LogUtil
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.TaskletStep
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
open class SimpleJob1Configuration constructor(
    private val logUtil: LogUtil,
    private val transactionManager: PlatformTransactionManager
) : DefaultBatchConfiguration() {

    @Bean
    open fun simpleJob1(jobRepository: JobRepository) =
        JobBuilder("simpleJob1", jobRepository)
            .listener(simpleJob1Listener())
            .listener(simpleJob1OtherListener())
            .incrementer(RunIdIncrementer())
            .start(simpleStep1(jobRepository))
            .build()

    @Bean
    open fun simpleStep1(jobRepository: JobRepository): TaskletStep =
        StepBuilder("simpleStep1", jobRepository)
            .tasklet(simpleJob1Task1(), transactionManager)
            .build()

    @Bean
    open fun simpleJob1Task1() = SimpleJob1Task1(logUtil)

    @Bean
    open fun simpleJob1Listener() = SimpleJob1Listener(logUtil)

    @Bean
    open fun simpleJob1OtherListener() = SimpleJob1OtherListener(logUtil)
}
