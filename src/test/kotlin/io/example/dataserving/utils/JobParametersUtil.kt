package io.example.dataserving.utils

import io.example.dataserving.job.incrementer.DateIncrementer
import org.springframework.batch.core.JobParameters

class JobParametersUtil {

    fun defaultJobParameters(): JobParameters {
        val dateIncrementer = DateIncrementer()
        return dateIncrementer.getNext(null)
    }
}