package com.example.batchapp.mysql.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.DataSourceInitializer
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.jdbc.support.JdbcTransactionManager
import java.sql.SQLException
import javax.sql.DataSource

@Configuration
open class BatchDataSourceConfiguration {

    @Bean("batchDataSource")
    @ConfigurationProperties("batch.datasource.hikari")
    @Throws(SQLException::class)
    open fun batchDataSource(): DataSource = DataSourceBuilder.create().build()

    @Bean
    open fun dataSourceInitializer(@Qualifier("batchDataSource") dataSource: DataSource): DataSourceInitializer =
        DataSourceInitializer().apply {

            /**
             * Add Batch Application Meta Table
             */
            val databasePopulator = ResourceDatabasePopulator().apply {
                this.addScripts(
                    ClassPathResource("/org/springframework/batch/core/schema-drop-mysql.sql"),
                    ClassPathResource("/org/springframework/batch/core/schema-mysql.sql")
                )
            }

            this.setDataSource(dataSource)
            this.setDatabasePopulator(databasePopulator)
        }

    @Bean
    open fun batchTransactionManager(@Qualifier("batchDataSource") dataSource: DataSource): JdbcTransactionManager =
        JdbcTransactionManager(dataSource)
}

