package com.example.batchapp.job

import com.example.batchapp.config.AbstractJobTestConfiguration
import com.example.batchapp.mysql.config.MysqlDataSourceConfiguration
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.batch.core.BatchStatus
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig


@SpringJUnitConfig(
    classes = [
        RunningJob4Configuration::class,
        MysqlDataSourceConfiguration::class
    ]
)
class RunningJob4ConfigurationTest : AbstractJobTestConfiguration() {

    @Test
    fun `Step 테스트 케이스`() {

        val exec = jobLauncherTestUtils.launchStep("runningStep4")

        assertEquals(BatchStatus.COMPLETED, exec.status)
    }

}
