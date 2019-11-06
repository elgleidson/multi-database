package com.github.elgleidson.multi.database.config;

import com.github.elgleidson.multi.database.domain.db2.Bar;
import com.github.elgleidson.multi.database.repository.db2.BarRepository;
import com.github.elgleidson.multi.database.util.DataSourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	basePackageClasses = { Bar.class, BarRepository.class },
	entityManagerFactoryRef="barEntityManagerFactory",
	transactionManagerRef="barTransactionManager"
)
public class BarDatabaseConfig {
	
	@Autowired
	private BarDatabaseProperties databaseProperties;
	
//	@Primary
	@Bean("barDataSource")
	public DataSource dataSource() {
		return DataSourceUtil.createDataSource(
				databaseProperties.getDatabase().getUrl(),
				databaseProperties.getDatabase().getUsername(),
				databaseProperties.getDatabase().getPassword()
		);
	}

	
//	@Primary
	@Bean("barEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("barDataSource") DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setPackagesToScan(Bar.class.getPackage().getName(), BarRepository.class.getPackage().getName());
		factoryBean.setPersistenceUnitName("barPersistenceUnit");
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factoryBean.setJpaPropertyMap(databaseProperties.getJpa().getProperties());
		return factoryBean;
	}
	
//	@Primary
	@Bean("barTransactionManager")
	public JpaTransactionManager barTransactionManager(@Qualifier("barEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
}
