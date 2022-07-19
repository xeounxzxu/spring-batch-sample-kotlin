package io.example.dataserving.job

import io.example.dataserving.task.SimpleJob1Task1
import io.example.dataserving.utils.LogUtil
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SimpleJob1Configuration constructor(
    val jobBuilderFactory: JobBuilderFactory,
    val stepBuilderFactory: StepBuilderFactory,
    val logUtil: LogUtil
) {

    @Bean
    fun simpleJob1() =
        //  simpleJob 이라는 Batch Job 을 생성
        jobBuilderFactory.get("simpleJob1")
            .start(simpleStep1())
            .build()

    @Bean
    fun simpleStep1() =
        // simpleStep1 이라는 Batch Step 생성
        stepBuilderFactory.get("simpleStep1")
            // Step 안에 수행 될 기능을 명시
            // tasklet 단일로 수행될 커스트텀한 기능들을 선언할 때 사용
            .tasklet(simpleJob1Task1())
            .build()

    @Bean
    fun simpleJob1Task1() = SimpleJob1Task1(logUtil)
}
