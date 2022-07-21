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
@Profile("/!mysql")
class H2Configuration constructor(
    private val logUtil: LogUtil
) {

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    @Throws(SQLException::class)
    fun dataSource(): HikariDataSource? {
        logUtil.getLogger().info("Started H2 DataBase ==============")
        Server.createTcpServer("-tcp", "-tcpAllowOthers", "-ifNotExists", "-tcpPort", 9095.toString() + "")
            .start()
        return HikariDataSource()
    }
}