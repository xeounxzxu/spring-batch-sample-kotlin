package com.example.batchapp.job

import TestBatchConfiguration
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration

@SpringBatchTest
@ContextConfiguration(
    classes = [
        TestBatchConfiguration::class,
    ]
)
abstract class AbstractJobConfigurationTest {

    @Autowired
    protected lateinit var jobLauncherTestUtils: JobLauncherTestUtils
}

