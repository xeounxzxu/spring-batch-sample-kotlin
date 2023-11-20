package com.example.batchapp

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
@EnableBatchProcessing(
    dataSourceRef = "batchDataSource", transactionManagerRef = "batchTransactionManager"
)
open class BootApplication

fun main(args: Array<String>) {
    runApplication<BootApplication>(*args)
}
