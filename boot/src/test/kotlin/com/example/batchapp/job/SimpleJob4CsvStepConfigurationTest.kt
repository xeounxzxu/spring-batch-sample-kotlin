package com.example.batchapp.job

import com.example.batchapp.config.TestBatchConfiguration
import com.example.batchapp.utils.JobParametersUtil
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBatchTest
@SpringBootTest(
    classes = [
        TestBatchConfiguration::class,
        SimpleJob4CsvStepConfiguration::class
    ]
)
internal class SimpleJob4CsvStepConfigurationTest {

    @Autowired
    private lateinit var jobLauncherTestUtils: JobLauncherTestUtils

    @Autowired
    private lateinit var mockJobParameters: JobParametersUtil

    private val mockJobName = "simpleJob4"

    @AfterEach
    fun cleanUp() {
    }

    @Test
    fun `Json 데이터 적재 하는 배치 테스트 케이스 Success`() {

        // given
        val parameters = mockJobParameters.defaultJobParameters()

        // when
        val jobExecution = jobLauncherTestUtils.launchJob(parameters)

        // then
        Assertions.assertEquals(jobExecution.jobInstance.jobName, mockJobName)
        Assertions.assertEquals(jobExecution.exitStatus.exitCode, ExitStatus.COMPLETED.exitCode)
    }

    @Test
    fun `simpleJob4Step1 성공적으로 작동하는지 유무 체크`() {

        // given
        val mockStepName = "simpleJob4Step1"

        // when
        val stepExecution = jobLauncherTestUtils.launchStep(mockStepName)

        // then
        Assertions.assertEquals(BatchStatus.COMPLETED, stepExecution.status)
    }
}
