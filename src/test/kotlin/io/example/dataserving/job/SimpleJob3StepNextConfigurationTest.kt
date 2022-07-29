package io.example.dataserving.job

import io.example.dataserving.config.TestBatchLegacyConfiguration
import io.example.dataserving.utils.JobParametersUtil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    classes = [
        TestBatchLegacyConfiguration::class,
        SimpleJob3StepNextConfiguration::class
    ]
)
@SpringBatchTest
internal class SimpleJob3StepNextConfigurationTest {

    @Autowired
    private lateinit var jobLauncherTestUtils: JobLauncherTestUtils

    @Autowired
    private lateinit var mockJobParameters: JobParametersUtil

    private val mockJobName = "job3"

    @Test
    fun `job3 테스트 케이스`() {

        val mock = mockJobParameters.defaultJobParameters()

        val job = jobLauncherTestUtils.launchJob(mock)

        assertEquals(job.jobInstance.jobName, mockJobName)
        assertEquals(job.exitStatus.exitCode, ExitStatus.COMPLETED.exitCode)
    }

    @Test
    fun `jobStep3-1 스탭 1 테스트 케이스`() {

        val mockStepName = "jobStep3-1"

        val step = jobLauncherTestUtils.launchStep(mockStepName)

        assertEquals(step.status, BatchStatus.COMPLETED)
    }

    @Test
    fun `jobStep3-2 스텝2 테스트 케이스`() {

        val mockStepName = "jobStep3-2"

        val step = jobLauncherTestUtils.launchStep(mockStepName)

        assertEquals(step.status, BatchStatus.COMPLETED)
    }

    @Test
    fun `jobStep3-3 스탭3 테스트케이스`() {

        val mockStepName = "jobStep3-3"

        val step = jobLauncherTestUtils.launchStep(mockStepName)

        assertEquals(step.status, BatchStatus.COMPLETED)
    }
}