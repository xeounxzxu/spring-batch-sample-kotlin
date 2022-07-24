package io.example.dataserving

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
@EnableBatchProcessing
class DataServingApplication

fun main(args: Array<String>) {
    runApplication<DataServingApplication>(*args)
}
