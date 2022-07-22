package io.example.dataserving.config

import com.zaxxer.hikari.HikariDataSource
import io.example.dataserving.utils.LogUtil
import org.h2.tools.Server
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import java.sql.SQLException

@Configuration
class DataSourceConfiguration constructor(
    private val logUtil: LogUtil
) {

    @Bean("dataSource")
    @Profile("!mysql")
    @ConfigurationProperties("spring.datasource.hikari")
    @Throws(SQLException::class)
    fun dataSourceH2(): HikariDataSource? {
        logUtil.getLogger().info("Started H2 DataBase ==============")

        Server.createTcpServer("-tcp", "-tcpAllowOthers", "-ifNotExists", "-tcpPort", 9095.toString() + "")
            .start()

        return HikariDataSource()
    }

    @Bean
    @Profile("mysql")
    @ConfigurationProperties("spring.datasource.hikari")
    fun dataSource(): HikariDataSource {
        return HikariDataSource()
    }
}
