package com.example.batchapp.job.tasklet

import com.example.batchapp.utils.LoggerUtil
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import kotlin.math.log

class SimpleJob1Task1 constructor(
    private val logger: LoggerUtil
) : Tasklet, StepExecutionListener {

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        logger.info("===> simpleStep1")
        return RepeatStatus.FINISHED
    }

    /**
     * Step 이 시작 전 포인트
     */
    override fun beforeStep(stepExecution: StepExecution) {
        logger.info("before point")
    }

    /**
     * Step 이 시작 후 포인트
     */
    override fun afterStep(stepExecution: StepExecution): ExitStatus? {
        logger.info("after point")
        return ExitStatus.COMPLETED
    }
}
