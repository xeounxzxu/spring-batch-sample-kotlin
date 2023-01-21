//package com.example.batchapp.config
//
//import org.springframework.boot.context.properties.ConfigurationProperties
//import org.springframework.boot.jdbc.DataSourceBuilder
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories
//import org.springframework.orm.jpa.JpaTransactionManager
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
//import org.springframework.transaction.PlatformTransactionManager
//import javax.sql.DataSource
//
//
////@Configuration
////@EnableJpaAuditing
////@EnableJpaRepositories(
////    basePackages = ["com.example.batchapp.repository"],
////    entityManagerFactoryRef = "mainEntityManager",
////    transactionManagerRef = "transactionManager"
////)
//open class MainDataSourceConfiguration {
//
//    @Bean
//    @ConfigurationProperties(prefix = "main2.datasource.hikari")
//    open fun mainDataSource(): DataSource = DataSourceBuilder.create().build()
//
//    @Bean
//    open fun mainEntityManager(): LocalContainerEntityManagerFactoryBean {
//        val em = LocalContainerEntityManagerFactoryBean()
//        em.dataSource = mainDataSource()
//        em.setPackagesToScan(*arrayOf("com.example.batchapp.domain"))
//        val vendorAdapter = HibernateJpaVendorAdapter()
//        em.jpaVendorAdapter = vendorAdapter
//
//        return em
//    }
//
//    @Bean
//    open fun transactionManager(): PlatformTransactionManager = JpaTransactionManager().let {
//        it.entityManagerFactory = mainEntityManager().getObject()
//        it
//    }
//}
