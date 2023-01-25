import com.example.batchapp.utils.JobParametersUtil
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.jdbc.support.JdbcTransactionManager
import javax.sql.DataSource


@TestConfiguration
@EnableBatchProcessing(
    dataSourceRef = "batchDataSource", transactionManagerRef = "batchTransactionManager"
)
open class BatchDataSourceTestConfiguration {

    @Bean
    open fun jobParametersUtil(): JobParametersUtil = JobParametersUtil()

    @Bean("batchDataSource")
    open fun batchDataSource(): DataSource = EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
        .addScript("/org/springframework/batch/core/schema-h2.sql").generateUniqueName(true).build()

    @Bean
    open fun batchTransactionManager(@Qualifier("batchDataSource") dataSource: DataSource): JdbcTransactionManager =
        JdbcTransactionManager(dataSource)
}
