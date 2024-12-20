package com.sfey.microservice.bean;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
//@EnableJpaRepositories(value = {"com.sfey.microservice.repository"})
@EnableTransactionManagement
public class DatasourceBeans {

    @Bean
    public HibernateJpaVendorAdapter jpaAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource, final HibernateJpaVendorAdapter jpaAdapter) {
        final var entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(jpaAdapter);
        entityManagerFactory.setPackagesToScan("com.sfey.microservice");
        entityManagerFactory.setPersistenceUnitName("default");
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
