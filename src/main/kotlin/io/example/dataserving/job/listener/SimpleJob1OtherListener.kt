package io.example.dataserving.job.listener

import io.example.dataserving.utils.LogUtil
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.annotation.AfterJob
import org.springframework.batch.core.annotation.BeforeJob

class SimpleJob1OtherListener constructor(
    private val logUtil: LogUtil
) {

    @BeforeJob
    fun before(jobExecution: JobExecution) {
        logUtil.getLogger().info(" ========= simple job other before Job")
    }

    @AfterJob
    fun after(jobExecution: JobExecution) {
        logUtil.getLogger().info(" ========= simple job other after Job")
    }
}
