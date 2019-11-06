package com.github.elgleidson.multi.database.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class LiquibaseConfig {

	@Autowired
	private FooDatabaseProperties fooDatabaseProperties;

	@Autowired
	private BarDatabaseProperties barDatabaseProperties;
	
//	@Primary
	@Bean("fooLiquibase")
	public SpringLiquibase fooLiquibase(@Qualifier("fooDataSource") DataSource dataSource) {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setDataSource(dataSource);
		liquibase.setChangeLog(fooDatabaseProperties.getLiquibase().getChangeLog());
		liquibase.setShouldRun(true);
		
		return liquibase;
	}
	
	@Bean("barLiquibase")
	public SpringLiquibase barLiquibase(@Qualifier("barDataSource") DataSource dataSource) {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setDataSource(dataSource);
		liquibase.setChangeLog(barDatabaseProperties.getLiquibase().getChangeLog());
		liquibase.setShouldRun(true);

		return liquibase;
	}
}
