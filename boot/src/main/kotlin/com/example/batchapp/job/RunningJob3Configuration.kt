package com.example.batchapp.job

import org.slf4j.LoggerFactory
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.TaskletStep
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.support.ListItemReader
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
open class RunningJob3Configuration(
    @Qualifier("mysqlTransactionManager")
    private val transactionManager: PlatformTransactionManager
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    open fun itemReaderByJob3(): ListItemReader<String> = ListItemReader(listOf("foo", "bar", "baz"))

    @Bean
    open fun itemWriterByJob3(): ItemWriter<String> = ItemWriter { s ->
        logger.info(s.toString())
    }

    @Bean
    open fun running3Step1(jobRepository: JobRepository): TaskletStep =
        StepBuilder("running3Step1", jobRepository)
            .chunk<String, String>(10, transactionManager)
            .reader(itemReaderByJob3())
            .writer(itemWriterByJob3())
            .build()

    @Bean
    open fun running3Job(jobRepository: JobRepository) = JobBuilder("running3Job", jobRepository)
        .start(running3Step1(jobRepository))
        .build()
}
