package com.example.batchapp.utils

import com.example.batchapp.core.incrementer.DateIncrementer
import org.springframework.batch.core.JobParameters

class JobParametersUtil {

    fun defaultJobParameters(): JobParameters {
        val dateIncrementer = DateIncrementer()
        return dateIncrementer.getNext(null)
    }
}
