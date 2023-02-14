package com.example.batchapp.runner.web

import com.example.batchapp.core.incrementer.DateIncrementer
import org.springframework.batch.core.Job
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("hello")
class HelloJobController constructor(
    @Qualifier("helloJob")
    private val job: Job,
    private val jobLauncher: JobLauncher,
    private val dateIncrementer: DateIncrementer
) {

    @GetMapping("job1")
    fun runningByHelloJob(): Map<String, Any> {

        val jobParam = dateIncrementer.getNext(null)

        jobLauncher.run(job, jobParam)

        return mapOf(
            "status" to HttpStatus.OK.value(), "data" to HttpStatus.OK.name
        )
    }

}
