package com.example.batchapp.config

import com.zaxxer.hikari.HikariDataSource
import com.example.batchapp.utils.LogUtil
import org.h2.tools.Server
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import java.sql.SQLException

@Configuration
open class DataSourceConfiguration constructor(
    private val logUtil: LogUtil
) {

    @Bean("dataSource")
    @ConfigurationProperties("spring.datasource.hikari")
    @Throws(SQLException::class)
    open fun mainDataSource(): HikariDataSource? {
        logUtil.getLogger().info("Started H2 DataBase ==============")

        Server.createTcpServer("-tcp", "-tcpAllowOthers", "-ifNotExists", "-tcpPort", 9095.toString() + "").start()

        return HikariDataSource()
    }
}
