package com.example.batchapp.job

import TestBatchConfiguration
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.Job
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig

@SpringBatchTest
@SpringJUnitConfig(
    classes = [TestBatchConfiguration::class, RunningJob1Configuration::class]
)
@DisplayName("RunningJob1 에서")
class RunningJob1ConfigurationTest {

    @Autowired
    private lateinit var jobLauncherTestUtils: JobLauncherTestUtils


    @Test
    fun `Running Job 의 상태 체크 테스트 케이스`(@Qualifier("runningJob") job: Job) {

        jobLauncherTestUtils.job = job

        val status = jobLauncherTestUtils.launchJob()

        assertEquals(ExitStatus.COMPLETED.exitCode, status.exitStatus.exitCode)
    }

}
