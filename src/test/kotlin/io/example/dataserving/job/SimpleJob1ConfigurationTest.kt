package io.example.dataserving.job

import io.example.dataserving.config.TestBatchConfiguration
import io.example.dataserving.utils.JobParametersUtil
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    classes = [
        TestBatchConfiguration::class,
        SimpleJob1Configuration::class
    ]
)
@SpringBatchTest
internal class SimpleJob1ConfigurationTest {

    @Autowired
    lateinit var jobLauncherTestUtils: JobLauncherTestUtils

    @Autowired
    private lateinit var mockJobParameters: JobParametersUtil

    @Test
    fun `simpleJob1 테스트 케이스`() {

        val mockJob = jobLauncherTestUtils.launchJob(mockJobParameters.defaultJobParameters())

        Assertions.assertEquals(mockJob.jobInstance.jobName, "simpleJob1")
        Assertions.assertEquals(mockJob.exitStatus.exitCode, ExitStatus.COMPLETED.exitCode)
    }

    @Test
    fun `simpleStep1 성공 테스트 케이스`() {

        // given
        val mockStepName = "simpleStep1"

        // when
        val step = jobLauncherTestUtils.launchStep(mockStepName)

        // then
        Assertions.assertEquals(step.status, BatchStatus.COMPLETED)
    }
}