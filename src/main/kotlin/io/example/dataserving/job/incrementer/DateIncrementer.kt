package io.example.dataserving.job.incrementer

import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.JobParametersIncrementer
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.Date

@Component
class DateIncrementer : JobParametersIncrementer {

    private val format = SimpleDateFormat("yyyyMMdd hh:mm:ss")

    override fun getNext(parameters: JobParameters?): JobParameters =
        JobParametersBuilder()
            .let {
                val id = format.format(Date())
                it.addString("run.id", id)
                it.toJobParameters()
            }
}