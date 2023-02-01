package com.example.batchapp.job

import BatchDataSourceTestConfiguration
import org.junit.jupiter.api.BeforeEach
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.JdbcOperations
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.sql.DataSource

@SpringBatchTest
@ContextConfiguration(
    classes = [
        BatchDataSourceTestConfiguration::class,
    ]
)
@Transactional(value = "mainTransactionManager", propagation = Propagation.NOT_SUPPORTED)
abstract class AbstractJobConfigurationTest {

    @Autowired
    protected lateinit var jobLauncherTestUtils: JobLauncherTestUtils

    @Autowired
    @Qualifier("mainDataSource")
    private lateinit var dataSource: DataSource

    private lateinit var jdbcTemplate: JdbcOperations

    @BeforeEach
    fun init() {
        this.jdbcTemplate = JdbcTemplate(dataSource)
    }
}

