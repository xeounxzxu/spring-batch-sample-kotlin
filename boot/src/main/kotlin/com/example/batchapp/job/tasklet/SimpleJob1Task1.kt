package com.example.batchapp.job.tasklet

import mu.KotlinLogging
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus

private val logger = KotlinLogging.logger { }

class SimpleJob1Task1 : Tasklet, StepExecutionListener {

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus =
        RepeatStatus.FINISHED.run {
            logger.info { "Running1 Execute By Tasklet" }
            this
        }

    /**
     * Step 이 시작 전 포인트
     */
    override fun beforeStep(stepExecution: StepExecution) {
        logger.info { "before point" }
    }

    /**
     * Step 이 시작 후 포인트
     */
    override fun afterStep(stepExecution: StepExecution): ExitStatus = ExitStatus.COMPLETED.run {
        logger.info { "this is After Step" }
        this
    }
}
