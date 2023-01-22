package com.example.batchapp.job.listener

import com.example.batchapp.utils.LoggerUtil
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.annotation.AfterJob
import org.springframework.batch.core.annotation.BeforeJob

class SimpleJob1OtherListener constructor(
    private val logger: LoggerUtil
) {

    @BeforeJob
    fun before(jobExecution: JobExecution) {
        logger.info(" ========= simple job other before Job")
    }

    @AfterJob
    fun after(jobExecution: JobExecution) {
        logger.info(" ========= simple job other after Job")
    }
}
