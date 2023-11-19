package com.example.batchapp.job

import com.example.batchapp.mysql.domain.User
import com.example.batchapp.runner.dto.UserSmailInfoDTO
import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.TaskletStep
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager

/**
 * CSV 에 데이터을 테이블로 적제하는 Class 입니다.
 */

@Configuration
open class RunningJob4Configuration constructor(
    @Qualifier("mysqlEntityManager")
    private val entityManagerFactory: LocalContainerEntityManagerFactoryBean,
    @Qualifier("mysqlTransactionManager")
    private val transactionManager: PlatformTransactionManager
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    private val chunkSize = 10

    @Bean
    open fun runningJob4(jobRepository: JobRepository): Job =
        JobBuilder("runningJob4", jobRepository)
            .start(runningStep4(jobRepository))
            .build()


    @Bean
    open fun runningStep4(jobRepository: JobRepository): TaskletStep =
        StepBuilder("runningStep4", jobRepository)
            .chunk<User, UserSmailInfoDTO>(chunkSize, transactionManager)
            .reader(job4JpaReader())
            .processor(csvItemJob4Processor())
            .writer(job4CustomWriter())
            .build()

    @Bean
    open fun job4JpaReader(): JpaPagingItemReader<User> {
        return JpaPagingItemReaderBuilder<User>()
            .name("job4JpaReader")
            .entityManagerFactory(entityManagerFactory.nativeEntityManagerFactory)
            .queryString("select u from USERS u")
            .build()
    }

    @Bean
    open fun csvItemJob4Processor(): ItemProcessor<User, UserSmailInfoDTO> =
        ItemProcessor<User, UserSmailInfoDTO> {
            UserSmailInfoDTO(it.email, it.point)
        }

    @Bean
    open fun job4CustomWriter(): ItemWriter<UserSmailInfoDTO> = ItemWriter {
        logger.info("custom iter : ${it.toString()}")
    }

}
