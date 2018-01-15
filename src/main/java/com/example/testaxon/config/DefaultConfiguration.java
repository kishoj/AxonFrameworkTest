package com.example.testaxon.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@Profile("default")
public class DefaultConfiguration {

	@Bean
	public JpaEventStorageEngine eventStorageEngine(Serializer serializer, DataSource dataSource,
			SingleEventUpcaster myUpcaster, EntityManagerProvider entityManagerProvider,
			TransactionManager transactionManager) throws SQLException {
		return new JpaEventStorageEngine(serializer, myUpcaster::upcast, dataSource, entityManagerProvider,
				transactionManager);
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.jpa")
	public Map<String, String> jpaProperties() {
		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.ddl-auto", "update");
		properties.put("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
		properties.put("hibernate.dialect", "com.example.testaxon.config.AxonCustomPostgreSQLDialect");		
		return properties;
	}
	
	@Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        return hibernateJpaVendorAdapter;
    }

	@Bean(name = "entityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource dataSource) {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan("com.example.testaxon", "org.axonframework.eventsourcing.eventstore.jpa",
				"org.axonframework.eventhandling.saga.repository.jpa",
				"org.axonframework.eventhandling.tokenstore.jpa");
		em.setJpaVendorAdapter(jpaVendorAdapter());
		em.setJpaPropertyMap(jpaProperties());
		em.setPersistenceUnitName("persistenceUnit");
		em.setMappingResources("orm.xml");
		return em;
	}
}
