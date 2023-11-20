package com.example.batchapp.config

import com.zaxxer.hikari.HikariDataSource
import org.h2.tools.Server
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.DataSourceInitializer
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.jdbc.support.JdbcTransactionManager
import java.sql.SQLException
import javax.sql.DataSource


@Configuration
open class BatchDataSourceConfiguration {

    @Bean("batchDataSource")
    @ConfigurationProperties("spring.datasource.hikari")
    @Throws(SQLException::class)
    open fun batchDataSource(): DataSource = HikariDataSource().apply {
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
    @Primary
    open fun batchTransactionManager(@Qualifier("batchDataSource") dataSource: DataSource): JdbcTransactionManager =
        JdbcTransactionManager(dataSource)
}
