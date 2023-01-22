package com.example.batchapp.web

import com.example.batchapp.job.dto.MsgDTO
import com.example.batchapp.job.incrementer.DateIncrementer
import org.springframework.batch.core.Job
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("batch")
class JobLauncherController constructor(
    private val jobLauncher: JobLauncher,
    @Qualifier("runningJob")
    private val job: Job,
    private val dateIncrementer: DateIncrementer
) {

    @GetMapping("running1")
    fun runningStartBatchJob(): String {

        val jobParameters = dateIncrementer.getNext(null)

        jobLauncher.run(job, jobParameters)

        return "OK"
    }

    @Deprecated("아직 사용 불가 Job")
    @PostMapping("csv")
    fun runCsvJob(): MsgDTO {

        val jobParameters = dateIncrementer.getNext(null)

        jobLauncher.run(job, jobParameters)

        return MsgDTO(HttpStatus.OK.value(), "SUCCESS")
    }
}
