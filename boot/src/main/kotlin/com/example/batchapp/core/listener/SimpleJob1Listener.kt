package com.example.batchapp.core.listener

import mu.KotlinLogging
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener

private val logger = KotlinLogging.logger { }

open class SimpleJob1Listener : JobExecutionListener {

    override fun beforeJob(jobExecution: JobExecution) {
        logger.info { " ========= simple job before Job" }
    }

    override fun afterJob(jobExecution: JobExecution) {
        logger.info { " ========= simple job after Job" }
    }
}
