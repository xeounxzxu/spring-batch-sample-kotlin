package com.example.yamlreader

import org.springframework.boot.SpringApplication
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.core.env.ConfigurableEnvironment

@Order(value = Ordered.LOWEST_PRECEDENCE) // 최상단 등록
class YamlEnvironmentPostProcessor : EnvironmentPostProcessor {


    override fun postProcessEnvironment(environment: ConfigurableEnvironment?, application: SpringApplication?) {

        val system = environment?.propertySources ?: throw IllegalStateException() // notnull

        return
    }
}
