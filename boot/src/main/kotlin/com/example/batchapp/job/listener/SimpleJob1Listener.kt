package com.example.batchapp.job.listener

import com.example.batchapp.utils.LoggerUtil.getLogger
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener

open class SimpleJob1Listener : JobExecutionListener {

    private val logger = getLogger(this::class.java)

    override fun beforeJob(jobExecution: JobExecution) {
        logger.info(" ========= simple job before Job")
    }

    override fun afterJob(jobExecution: JobExecution) {
        logger.info(" ========= simple job after Job")
    }
}
