package io.example.dataserving.config

import com.zaxxer.hikari.HikariDataSource
import org.h2.tools.Server
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ContextStoppedEvent
import org.springframework.context.event.EventListener
import java.sql.SQLException

@Configuration
class H2Configuration {

    private var server: Server? = null

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    @Throws(SQLException::class)
    fun dataSource(): HikariDataSource? {

        server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-ifNotExists", "-tcpPort", 9095.toString() + "")
            .start()

        return HikariDataSource()
    }

    @EventListener(ContextStoppedEvent::class)
    fun stopServer() {
        server!!.stop()
    }
}
