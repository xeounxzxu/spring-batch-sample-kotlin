package com.example.batchapp.job

import com.example.batchapp.config.AbstractJobTestConfiguration
import com.example.batchapp.config.MainDataSourceTestConfiguration
import com.example.batchapp.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.Job
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig

@SpringJUnitConfig(classes = [RunningJob2Configuration::class, MainDataSourceTestConfiguration::class])
open class `RunningJob2 에서` : AbstractJobTestConfiguration() {

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun `성공 테스트 케이스`(@Qualifier("runningJob2") job: Job) {

        jobLauncherTestUtils.job = job

        val status = jobLauncherTestUtils.launchJob()

        assertEquals(ExitStatus.COMPLETED.exitCode, status.exitStatus.exitCode)

        val cnt = userRepository.count()

        assertEquals(1001, cnt)
    }
}
