package com.example.openfeignapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OpenFeignApiApplication

fun main(args: Array<String>) {
    runApplication<OpenFeignApiApplication>(*args)
}
