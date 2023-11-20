package com.example.batchapp.orcale.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import java.util.*
import javax.sql.DataSource


@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
    basePackages = ["com.example.batchapp.orcale.repository"],
    entityManagerFactoryRef = "orcaleEntityManager",
    transactionManagerRef = "orcaleTransactionManager"
)
@EntityScan(
    basePackages = ["com.example.batchapp.orcale.domain"]
)
open class OracleDataSourceConfiguration {

    private fun getJpaProperties(): Properties {
        return object : Properties() {
            init {
                setProperty("hibernate.hbm2ddl.auto", "create-drop")
                setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect")
                setProperty("hibernate.show_sql", "true")
                setProperty("hibernate.format_sql", "true")
            }
        }
    }

    @Bean("orcaleDataSource")
    @ConfigurationProperties(prefix = "main3.datasource.hikari")
    open fun orcaleDataSource(): DataSource = DataSourceBuilder.create().build()

    @Bean("orcaleEntityManager")
    open fun orcaleEntityManager(
        @Qualifier("orcaleDataSource") dataSource: DataSource, jpaVendorAdapter: JpaVendorAdapter
    ): LocalContainerEntityManagerFactoryBean {

        val em = LocalContainerEntityManagerFactoryBean()

        em.persistenceUnitName = "jpa";

        em.dataSource = dataSource

        em.setPackagesToScan(*arrayOf("com.example.batchapp.orcale.domain"))

        em.jpaVendorAdapter = jpaVendorAdapter

        em.setJpaProperties(getJpaProperties());

        return em
    }

    @Bean
    open fun jpaVendorAdapter(): JpaVendorAdapter? {
        val hibernateJpaVendorAdapter = HibernateJpaVendorAdapter()
        hibernateJpaVendorAdapter.setShowSql(true)
        hibernateJpaVendorAdapter.setGenerateDdl(true)
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL)
        return hibernateJpaVendorAdapter
    }

    @Bean("oracleTransactionManager")
    open fun oracleTransactionManager(
        mainEntityManager: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager = JpaTransactionManager().apply {
        this.entityManagerFactory = mainEntityManager.getObject()
    }
}
