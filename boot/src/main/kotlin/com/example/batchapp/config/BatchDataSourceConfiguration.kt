package com.example.batchapp.config

import com.example.batchapp.utils.LoggerUtil
import com.zaxxer.hikari.HikariDataSource
import org.h2.tools.Server
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.DataSourceInitializer
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
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

    @Bean("batchDataSource")
    @ConfigurationProperties("spring.datasource.hikari")
    @Throws(SQLException::class)
    open fun batchDataSource(): DataSource = HikariDataSource().apply {
        logger.info("Started H2 DataBase By TCP")
        Server.createTcpServer("-tcp", "-tcpAllowOthers", "-ifNotExists", "-tcpPort", 9095.toString() + "").start()
    }

    @Bean
    open fun dataSourceInitializer(@Qualifier("batchDataSource") dataSource: DataSource): DataSourceInitializer =
        DataSourceInitializer().apply {

            /**
             * Add Batch Application Meta Table
             */
            val resourceDatabasePopulator = ResourceDatabasePopulator().let {
                it.addScript(ClassPathResource("/org/springframework/batch/core/schema-h2.sql"))
                it
            }

            this.setDataSource(dataSource)
            this.setDatabasePopulator(resourceDatabasePopulator)
        }

    @Bean
    open fun batchTransactionManager(@Qualifier("batchDataSource") dataSource: DataSource): JdbcTransactionManager =
        JdbcTransactionManager(dataSource)
}
