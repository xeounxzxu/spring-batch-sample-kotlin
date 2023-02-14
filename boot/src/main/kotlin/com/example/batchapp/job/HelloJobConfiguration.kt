package com.example.batchapp.job

import org.slf4j.LoggerFactory
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.core.step.tasklet.TaskletStep
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
open class HelloJobConfiguration constructor(
    @Qualifier("batchTransactionManager")
    private val transactionManager: PlatformTransactionManager
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    open fun helloJob(jobRepository: JobRepository) =
        JobBuilder("helloJob", jobRepository)
            .start(helloStep(jobRepository))
            .build()

    @Bean
    open fun helloStep(jobRepository: JobRepository): TaskletStep = StepBuilder("helloStep", jobRepository)
        .tasklet(Tasklet { contribution, chunkContext ->
            logger.info("hello step 1")
            return@Tasklet RepeatStatus.FINISHED
        }, transactionManager)
        .build()
}
