package com.example.batchapp.config

import com.example.batchapp.utils.LogUtil
import com.zaxxer.hikari.HikariDataSource
import org.h2.tools.Server
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.sql.SQLException

@Configuration
open class AppDataSourceConfiguration constructor(
    private val logUtil: LogUtil
) {

    @Primary
    @Bean("dataSource")
    @ConfigurationProperties("spring.datasource.hikari")
    @Throws(SQLException::class)
    open fun appDataSource(): HikariDataSource? {
        logUtil.getLogger().info("Started H2 DataBase ==============")

        Server.createTcpServer("-tcp", "-tcpAllowOthers", "-ifNotExists", "-tcpPort", 9095.toString() + "").start()

        return HikariDataSource()
    }
}
