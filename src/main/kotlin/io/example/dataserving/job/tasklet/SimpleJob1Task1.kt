package io.example.dataserving.job.tasklet

import io.example.dataserving.utils.LogUtil
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus

class SimpleJob1Task1 constructor(
    private val logUtil: LogUtil
) : Tasklet, StepExecutionListener {

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        logUtil.getLogger().info("===> simpleStep1")
        return RepeatStatus.FINISHED
    }

    /**
     * Step 이 시작 전 포인트
     */
    override fun beforeStep(stepExecution: StepExecution) {
        logUtil.getLogger().info("before point")
    }

    /**
     * Step 이 시작 후 포인트
     */
    override fun afterStep(stepExecution: StepExecution): ExitStatus? {
        logUtil.getLogger().info("after point")
        return ExitStatus.COMPLETED
    }
}