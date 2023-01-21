package com.example.batchapp.config

import com.example.batchapp.utils.LogUtil
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
open class AppDataSourceConfiguration constructor(
    private val logUtil: LogUtil
) {

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    @Throws(SQLException::class)
    open fun batchDataSource(): DataSource {
        logUtil.getLogger().info("Started H2 DataBase ==============")
        Server.createTcpServer("-tcp", "-tcpAllowOthers", "-ifNotExists", "-tcpPort", 9095.toString() + "").start()
        return HikariDataSource()
    }

    @Bean
    open fun batchTransactionManager(
        @Qualifier("batchDataSource") dataSource: DataSource
    ): JdbcTransactionManager = JdbcTransactionManager(dataSource)
}
