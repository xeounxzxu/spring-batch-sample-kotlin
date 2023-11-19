package com.example.batchapp.mysql.config

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
    basePackages = ["com.example.batchapp.mysql.repository"],
    entityManagerFactoryRef = "mainEntityManager",
    transactionManagerRef = "mainTransactionManager"
)
@EntityScan(
    basePackages = ["com.example.batchapp.mysql.domain"]
)
open class MainDataSourceConfiguration {

    private fun getJpaProperties(): Properties {
        return object : Properties() {
            init {
                setProperty("hibernate.hbm2ddl.auto", "create-drop")
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
                setProperty("hibernate.show_sql", "true")
                setProperty("hibernate.format_sql", "true")
            }
        }
    }

    @Bean("mainDataSource")
    @ConfigurationProperties(prefix = "main2.datasource.hikari")
    open fun mainDataSource(): DataSource = DataSourceBuilder.create().build()

    @Bean("mainEntityManager")
    open fun mainEntityManager(
        @Qualifier("mainDataSource") dataSource: DataSource, jpaVendorAdapter: JpaVendorAdapter
    ): LocalContainerEntityManagerFactoryBean {

        val em = LocalContainerEntityManagerFactoryBean()

        em.persistenceUnitName = "jpa";

        em.dataSource = dataSource

        em.setPackagesToScan(*arrayOf("com.example.batchapp.mysql.domain"))

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

    @Bean
    open fun mainTransactionManager(
        mainEntityManager: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager = JpaTransactionManager().apply {
        this.entityManagerFactory = mainEntityManager.getObject()
    }
}
