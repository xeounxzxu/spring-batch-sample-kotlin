package com.example.batchapp.job.listener

import com.example.batchapp.utils.LoggerUtil
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener

open class SimpleJob1Listener constructor(
    private val logger: LoggerUtil
) : JobExecutionListener {

    override fun beforeJob(jobExecution: JobExecution) {
        logger.info(" ========= simple job before Job")
    }

    override fun afterJob(jobExecution: JobExecution) {
        logger.info(" ========= simple job after Job")
    }
}
