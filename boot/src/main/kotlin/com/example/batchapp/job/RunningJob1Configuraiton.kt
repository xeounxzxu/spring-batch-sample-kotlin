package com.example.batchapp.job

import com.example.batchapp.job.tasklet.SimpleJob1Task1
import com.example.batchapp.utils.LoggerUtil
import org.springframework.batch.core.Job
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.TaskletStep
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
open class RunningJob1Configuraiton constructor(
    @Qualifier("batchTransactionManager")
    private val transactionManager: PlatformTransactionManager
) {

    @Bean
    open fun runningJob(jobRepository: JobRepository, @Qualifier("runningStep1") step: TaskletStep): Job =
        JobBuilder("runJob1", jobRepository)
            .start(step)
            .build()

    @Bean
    open fun runningStep1(jobRepository: JobRepository): TaskletStep = StepBuilder("step1", jobRepository)
        .tasklet(simpleJob1Task1(), transactionManager)
        .build()

    @Bean
    open fun simpleJob1Task1() = SimpleJob1Task1()

}
