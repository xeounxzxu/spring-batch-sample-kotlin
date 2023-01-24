package com.example.batchapp.job

import com.example.batchapp.domain.User
import com.example.batchapp.job.dto.UserDTO
import com.example.batchapp.repository.UserRepository
import org.springframework.batch.core.Job
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.TaskletStep
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.LineMapper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.transaction.PlatformTransactionManager

/**
 * CSV 에 데이터을 테이블로 적제하는 Class 입니다.
 */
@Configuration
open class RunningJob2Configuration constructor(
    private val userRepository: UserRepository,
    @Qualifier("mainTransactionManager") private val transactionManager: PlatformTransactionManager
) {

    @Bean
    open fun runningJob2(jobRepository: JobRepository): Job =
        JobBuilder("runningJob2", jobRepository).start(runningStep2(jobRepository)).build()


    @Bean
    open fun runningStep2(jobRepository: JobRepository): TaskletStep =
        StepBuilder("runningStep2", jobRepository).chunk<UserDTO, User>(100, transactionManager).reader(csvItemReader())
            .processor(csvItemProcessor()).writer(csvItemWriter()).build()

    @Bean
    open fun csvItemReader(): ItemReader<UserDTO> = FlatFileItemReader<UserDTO>().apply {
        this.setName("userReader")

        this.setEncoding("UTF-8")

        this.setResource(ClassPathResource("user.csv"))

        this.setLinesToSkip(1)

        this.setLineMapper(LineMapper { line, number ->
            val listArray = line.split(",")
            UserDTO(
                listArray[0].toLong(),
                listArray[3].toDouble(),
                listArray[4],
                listArray[5],
                listArray[6],
                listArray[7],
            )
        })
    }

    @Bean
    open fun csvItemProcessor(): ItemProcessor<UserDTO, User> = ItemProcessor<UserDTO, User> {
        it.toEntity()
    }

    @Bean
    open fun csvItemWriter() = ItemWriter<User> {
        userRepository.saveAll(it)
        it
    }
}
