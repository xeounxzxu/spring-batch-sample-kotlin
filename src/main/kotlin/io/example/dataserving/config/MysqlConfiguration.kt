package io.example.dataserving.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("mysql")
@Configuration
class MysqlConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    fun dataSource(): HikariDataSource {
        return HikariDataSource()
    }
}