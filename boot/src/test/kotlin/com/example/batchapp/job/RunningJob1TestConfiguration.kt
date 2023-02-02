package com.example.batchapp.job

import com.example.batchapp.config.AbstractBaseJobTestConfiguration
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.Job
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig

@SpringJUnitConfig(
    classes = [RunningJob1Configuration::class]
)
class RunningJob1TestConfiguration : AbstractBaseJobTestConfiguration() {


    @Test
    fun `Running Job 의 상태 체크 테스트 케이스`(@Qualifier("runningJob") job: Job) {

        jobLauncherTestUtils.job = job

        val status = jobLauncherTestUtils.launchJob()

        assertEquals(ExitStatus.COMPLETED.exitCode, status.exitStatus.exitCode)
    }

}
