package io.example.dataserving.job

import io.example.dataserving.job.incrementer.DateIncrementer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.batch.core.JobParameters
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.support.DirtiesContextTestExecutionListener

@ExtendWith(SpringExtension::class)
@SpringBatchTest
@EnableAutoConfiguration
@ContextConfiguration(
    classes = [
        SimpleJob4CsvStepConfiguration::class,
        DirtiesContextTestExecutionListener::class,
    ]
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
internal class SimpleJob4CsvStepConfigurationTest constructor(
    @Autowired
    private val jobLauncherTestUtils: JobLauncherTestUtils
) {

    @AfterEach
    fun cleanUp() {
        // jobRepositoryTestUtils.removeJobExecutions()
    }

    private fun defaultJobParameters(): JobParameters {
        val dateIncrementer = DateIncrementer()
        return dateIncrementer.getNext(null)
    }

    @Test
    fun test() {

        // when
        val jobExecution = jobLauncherTestUtils.launchJob(defaultJobParameters())
        val jobInstance = jobExecution.jobInstance
        val exitStatus = jobExecution.exitStatus
    }
}