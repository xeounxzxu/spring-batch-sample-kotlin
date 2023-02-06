package com.example.batchapp.runner.web

import com.example.batchapp.job.dto.MsgDTO
import com.example.batchapp.core.incrementer.DateIncrementer
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
    @Qualifier("runningJob") private val job: Job,
    @Qualifier("runningJob2") private val job2: Job,
    @Qualifier("runningJob4") private val job4: Job,
    private val dateIncrementer: DateIncrementer
) {

    @GetMapping("running1")
    fun runningStartBatchJob(): Map<String, Any> {

        val jobParameters = dateIncrementer.getNext(null)

        jobLauncher.run(job, jobParameters)

        return mapOf(
            "status" to HttpStatus.OK.value(), "data" to HttpStatus.OK.name
        )
    }

    @PostMapping("csv")
    fun runCsvJob(): MsgDTO {

        val jobParameters = dateIncrementer.getNext(null)

        jobLauncher.run(job2, jobParameters)

        return MsgDTO(HttpStatus.OK.value(), "SUCCESS")
    }

    @PostMapping("csv2")
    fun runJob4(): MsgDTO {

        val jobParameters = dateIncrementer.getNext(null)

        jobLauncher.run(job4, jobParameters)

        return MsgDTO(HttpStatus.OK.value(), "SUCCESS")
    }

}
