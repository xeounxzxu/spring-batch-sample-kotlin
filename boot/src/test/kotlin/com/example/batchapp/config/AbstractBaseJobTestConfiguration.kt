package com.example.batchapp.config

import BatchDataSourceTestConfiguration
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration


@SpringBatchTest
@ContextConfiguration(
    classes = [
        BatchDataSourceTestConfiguration::class,
    ]
)
abstract class AbstractBaseJobTestConfiguration {


    @Autowired
    protected lateinit var jobLauncherTestUtils: JobLauncherTestUtils
}
