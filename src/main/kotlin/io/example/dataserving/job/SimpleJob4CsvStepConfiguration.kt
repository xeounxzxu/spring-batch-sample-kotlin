package io.example.dataserving.job

import io.example.dataserving.domain.User
import io.example.dataserving.job.dto.UserDTO
import io.example.dataserving.repository.UserRepository
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.LineMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class SimpleJob4CsvStepConfiguration constructor(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    private val userRepository: UserRepository
) {
    @Bean
    fun simpleJob4() = jobBuilderFactory.get("simpleJob4")
        .start(simpleJob4Step1())
        .build()

    @Bean
    fun simpleJob4Step1() = stepBuilderFactory
        .get("simpleJob4Step1")
        .chunk<UserDTO, User>(100)
        .reader(csvItemReader())
        .processor(csvItemProcessor())
        .writer(csvItemWriter())
        .build()

    @Bean
    @Throws(Exception::class)
    fun csvItemReader(): FlatFileItemReader<UserDTO> = FlatFileItemReader<UserDTO>()
        .apply {

            this.setName("userReader")

            this.setEncoding("UTF-8")

            this.setResource(ClassPathResource("user.csv"))

            this.setLinesToSkip(1)

            this.setLineMapper(
                LineMapper { line, lineNumber ->

                    val listArray = line.split(",")

                    UserDTO(
                        listArray[0].toLong(),
                        listArray[3].toDouble(),
                        listArray[4],
                        listArray[5],
                        listArray[6],
                        listArray[7],
                    )
                }
            )
        }

    @Bean
    fun csvItemProcessor(): ItemProcessor<UserDTO, User> = ItemProcessor<UserDTO, User> {
        it.toEntity()
    }

    @Bean
    fun csvItemWriter() = ItemWriter<User> {
        userRepository.saveAll(it)
        it
    }
}
