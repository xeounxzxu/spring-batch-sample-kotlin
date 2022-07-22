package io.example.dataserving.job.listener

import io.example.dataserving.utils.LogUtil
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener

open class SimpleJob1Listener constructor(
    private val logUtil: LogUtil
) : JobExecutionListener {

    override fun beforeJob(jobExecution: JobExecution) {
        logUtil.getLogger().info(" ========= simple job before Job")
    }

    override fun afterJob(jobExecution: JobExecution) {
        logUtil.getLogger().info(" ========= simple job after Job")
    }
}
