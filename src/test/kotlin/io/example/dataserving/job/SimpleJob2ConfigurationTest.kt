package io.example.dataserving.job

import io.example.dataserving.config.TestBatchLegacyConfiguration
import io.example.dataserving.utils.JobParametersUtil
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    classes = [
        TestBatchLegacyConfiguration::class,
        SimpleJob2Configuration::class
    ]
)
@SpringBatchTest
internal class SimpleJob2ConfigurationTest {

    @Autowired
    private lateinit var jobLauncherTestUtils: JobLauncherTestUtils

    @Autowired
    private lateinit var mockJobParameters: JobParametersUtil

    @Test
    fun `simpleJob2 성공 테스트 케이스`() {

        // given
        val mock = mockJobParameters.defaultJobParameters()

        // when
        val job = jobLauncherTestUtils.launchJob(mock)

        // then
        Assertions.assertEquals(job.jobInstance.jobName, "simpleJob2")
        Assertions.assertEquals(job.exitStatus.exitCode, ExitStatus.COMPLETED.exitCode)
    }
}