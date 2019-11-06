package com.github.elgleidson.multi.database.config;

import com.github.elgleidson.multi.database.domain.db1.Foo;
import com.github.elgleidson.multi.database.repository.db1.FooRepository;
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
	basePackageClasses = { Foo.class, FooRepository.class },
	entityManagerFactoryRef="fooEntityManagerFactory",
	transactionManagerRef="fooTransactionManager"
)
public class FooDatabaseConfig {
	
	@Autowired
	private FooDatabaseProperties databaseProperties;
	
//	@Primary
	@Bean("fooDataSource")
	public DataSource dataSource() {
		return DataSourceUtil.createDataSource(
				databaseProperties.getDatabase().getUrl(),
				databaseProperties.getDatabase().getUsername(),
				databaseProperties.getDatabase().getPassword()
		);
	}

	
//	@Primary
	@Bean("fooEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("fooDataSource") DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setPackagesToScan(Foo.class.getPackage().getName(), FooRepository.class.getPackage().getName());
		factoryBean.setPersistenceUnitName("fooPersistenceUnit");
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factoryBean.setJpaPropertyMap(databaseProperties.getJpa().getProperties());
		return factoryBean;
	}
	
//	@Primary
	@Bean("fooTransactionManager")
	public JpaTransactionManager fooTransactionManager(@Qualifier("fooEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
}
