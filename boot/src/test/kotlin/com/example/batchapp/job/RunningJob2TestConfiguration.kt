package com.example.batchapp.job

import com.example.batchapp.config.AbstractJobTestConfiguration
import com.example.batchapp.config.MainDataSourceTestConfiguration
import com.example.batchapp.repository.UserRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.Job
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig

@SpringJUnitConfig(classes = [RunningJob2Configuration::class, MainDataSourceTestConfiguration::class])
open class `RunningJob2 에서` : AbstractJobTestConfiguration() {

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun `Step 테스트 케이스`() {

        val exec = jobLauncherTestUtils.launchStep("runningStep2")

        assertEquals(BatchStatus.COMPLETED, exec.status)

        val entity = userRepository.findById(1L).get()

        assertEquals(1, entity.id)
        assertEquals(24.0, entity.point)
        assertEquals("lbearcock0@privacy.gov.au", entity.email)
        assertEquals("W", entity.gender)
        assertEquals("Letizia", entity.firstName)
        assertEquals("Bearcock", entity.lastName)
    }

    @Test
    fun `성공 테스트 케이스`(@Qualifier("runningJob2") job: Job) {

        jobLauncherTestUtils.job = job

        val exec = jobLauncherTestUtils.launchJob()

        assertEquals(BatchStatus.COMPLETED, exec.status)

        val stepExec = exec.stepExecutions.iterator().next()

        assertEquals(BatchStatus.COMPLETED, stepExec.status)
        assertEquals(1001, stepExec.readCount)
        assertEquals(1001, stepExec.writeCount)

        val cnt = userRepository.count()

        assertEquals(1001, cnt)
    }

    @AfterEach
    fun after() {
        userRepository.deleteAllInBatch()
    }
}
