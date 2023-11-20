package com.example.batchapp.core.listener

import mu.KotlinLogging
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.annotation.AfterJob
import org.springframework.batch.core.annotation.BeforeJob

private val logger = KotlinLogging.logger { }

class SimpleJob1OtherListener {

    @BeforeJob
    fun before(jobExecution: JobExecution) {
        logger.info { " ========= simple job other before Job" }
    }

    @AfterJob
    fun after(jobExecution: JobExecution) {
        logger.info { " ========= simple job other after Job" }
    }
}
