package com.example.batchapp.core.listener

import com.example.batchapp.utils.LoggerUtil.getLogger
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.annotation.AfterJob
import org.springframework.batch.core.annotation.BeforeJob

class SimpleJob1OtherListener {

    private val logger = getLogger(this::class.java)

    @BeforeJob
    fun before(jobExecution: JobExecution) {
        logger.info(" ========= simple job other before Job")
    }

    @AfterJob
    fun after(jobExecution: JobExecution) {
        logger.info(" ========= simple job other after Job")
    }
}
