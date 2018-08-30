package edu.bankApp.interview.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableTransactionManagement
public class DataBaseConfiguration {


	public static final String DB_MYSQL_DRIVER_PROPERTY = "database.mysql.driver-class";
	public static final String DB_MYSQL_HOST_PROPERTY = "database.mysql.host";
	public static final String DB_MYSQL_PORT_PROPERTY = "database.mysql.port";
	public static final String DB_MYSQL_DATABASE_PROPERTY = "database.mysql.database";
	public static final String DB_MYSQL_USERNAME_PROPERTY = "database.mysql.username";
	public static final String DB_MYSQL_PASSWORD_PROPERTY = "database.mysql.password";
	
	public static final String HIBERNATE_HBM2DDL_PROPERTY = "hibernate.hbm2ddl.auto";
	public static final String HIBERNATE_DIALECT_PROPERTY = "hibernate.dialect";
	public static final String HIBERNATE_SHOW_SQL_PROPERTY = "hibernate.show_sql";
			
	@Autowired
	private Environment env;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan("edu.bankApp.interview");

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(env.getProperty(DB_MYSQL_DRIVER_PROPERTY));
		dataSource.setUrl("jdbc:mysql://"+env.getProperty(DB_MYSQL_HOST_PROPERTY)+":"+env.getProperty(DB_MYSQL_PORT_PROPERTY)+"/"+env.getProperty(DB_MYSQL_DATABASE_PROPERTY) +"?useSSL=false");
		dataSource.setUsername(env.getProperty(DB_MYSQL_USERNAME_PROPERTY));
		dataSource.setPassword(env.getProperty(DB_MYSQL_PASSWORD_PROPERTY));
		
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty(HIBERNATE_HBM2DDL_PROPERTY, env.getProperty(HIBERNATE_HBM2DDL_PROPERTY));
		properties.setProperty(HIBERNATE_DIALECT_PROPERTY, env.getProperty(HIBERNATE_DIALECT_PROPERTY));
		properties.setProperty(HIBERNATE_SHOW_SQL_PROPERTY, env.getProperty(HIBERNATE_SHOW_SQL_PROPERTY));
	
		return properties;
	}
	


}
