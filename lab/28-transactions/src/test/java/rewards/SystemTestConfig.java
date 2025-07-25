package rewards;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import config.RewardsConfig;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;


@Configuration
@Import(RewardsConfig.class)
public class SystemTestConfig {

	
	/**
	 * Creates an in-memory "rewards" database populated 
	 * with test data for fast testing
	 */
	@Bean
	public DataSource dataSource(){
		return
			(new EmbeddedDatabaseBuilder()) //
			.addScript("classpath:rewards/testdb/schema.sql") //
			.addScript("classpath:rewards/testdb/data.sql") //
			.build();
	}	
	
	
	//	TODO-02: Define a bean named 'transactionManager' that configures a
	//           DataSourceTransactionManager.
	//           How does it know which dataSource to manage?

	@Bean
	public PlatformTransactionManager transactionManager(){
		return new DataSourceTransactionManager(dataSource());
	}
}
