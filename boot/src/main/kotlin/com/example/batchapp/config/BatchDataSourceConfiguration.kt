package com.example.batchapp.config

import com.example.batchapp.utils.LoggerUtil
import com.zaxxer.hikari.HikariDataSource
import org.h2.tools.Server
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.support.JdbcTransactionManager
import java.sql.SQLException
import javax.sql.DataSource


@Configuration
@EnableBatchProcessing(
    dataSourceRef = "batchDataSource", transactionManagerRef = "batchTransactionManager"
)
open class BatchDataSourceConfiguration constructor(
    private val logger: LoggerUtil
) {

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    @Throws(SQLException::class)
    open fun batchDataSource(): DataSource {
        logger.info("Started H2 DataBase ==============")
        Server.createTcpServer("-tcp", "-tcpAllowOthers", "-ifNotExists", "-tcpPort", 9095.toString() + "").start()
        return HikariDataSource()
    }

    @Bean
    open fun batchTransactionManager(
        @Qualifier("batchDataSource") dataSource: DataSource
    ): JdbcTransactionManager = JdbcTransactionManager(dataSource)
}
