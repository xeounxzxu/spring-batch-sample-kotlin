package io.example.dataserving.job

import io.example.dataserving.config.TestBatchLegacyConfiguration
import io.example.dataserving.job.incrementer.DateIncrementer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.JobParameters
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBatchTest
@SpringBootTest(classes = [TestBatchLegacyConfiguration::class, SimpleJob4CsvStepConfiguration::class])
internal class SimpleJob4CsvStepConfigurationTest {

    @Autowired
    private lateinit var jobLauncherTestUtils: JobLauncherTestUtils

    @AfterEach
    fun cleanUp() {
    }

    private fun defaultJobParameters(): JobParameters {
        val dateIncrementer = DateIncrementer()
        return dateIncrementer.getNext(null)
    }

    @Test
    fun `Json 데이터 적재 하는 배치 테스트 케이스 Success`() {

        // given
        val parameters = defaultJobParameters()

        // when
        val jobExecution = jobLauncherTestUtils.launchJob(parameters)
        val jobInstance = jobExecution.jobInstance
        val exitStatus = jobExecution.exitStatus

        //then
        Assertions.assertEquals(BatchStatus.COMPLETED, jobExecution.status)
        Assertions.assertEquals(ExitStatus.COMPLETED, jobExecution.exitStatus)
    }

    @Test
    fun `simpleJob4Step1 성공적으로 작동하는지 유무 체크`() {

        val stepExecution = jobLauncherTestUtils.launchStep("simpleJob4Step1")

        Assertions.assertEquals(BatchStatus.COMPLETED, stepExecution.status)
    }
}
