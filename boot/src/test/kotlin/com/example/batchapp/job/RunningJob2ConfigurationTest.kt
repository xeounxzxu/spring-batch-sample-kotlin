package com.example.batchapp.job

import com.example.batchapp.config.MainDataSourceTestConfiguration
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.Job
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig

@SpringJUnitConfig(classes = [RunningJob2Configuration::class, MainDataSourceTestConfiguration::class])
open class RunningJob2ConfigurationTest : AbstractJobConfigurationTest() {

    @Test
    fun `csv 파일을 로드 해서 데이터 적제 Job 성공 테스트 케이스`(@Qualifier("runningJob2") job: Job) {

        jobLauncherTestUtils.job = job

        val status = jobLauncherTestUtils.launchJob()

        assertEquals(ExitStatus.COMPLETED.exitCode, status.exitStatus.exitCode)
    }
}
