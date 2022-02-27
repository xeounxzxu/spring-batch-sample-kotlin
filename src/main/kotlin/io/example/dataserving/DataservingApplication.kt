package io.example.dataserving

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DataservingApplication

fun main(args: Array<String>) {
    runApplication<DataservingApplication>(*args)
}
