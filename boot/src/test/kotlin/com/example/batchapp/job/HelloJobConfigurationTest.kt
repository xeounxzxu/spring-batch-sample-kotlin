package com.example.batchapp.job

import com.example.batchapp.config.AbstractBaseJobTestConfiguration
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.Job
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig

@SpringJUnitConfig(
    classes = [
        HelloJobConfiguration::class
    ]
)
class HelloJobConfigurationTest : AbstractBaseJobTestConfiguration() {

    @Test
    fun `hello job 성공적인 status 반환`(
        @Qualifier("helloJob")
        job: Job
    ) {


        jobLauncherTestUtils.job = job

        // job status check
        val exec = jobLauncherTestUtils.launchJob()

        assertEquals(BatchStatus.COMPLETED, exec.status)

        // step status check
        val stepExec = exec.stepExecutions.iterator().next()

        assertEquals(BatchStatus.COMPLETED, stepExec.status)
    }

}
