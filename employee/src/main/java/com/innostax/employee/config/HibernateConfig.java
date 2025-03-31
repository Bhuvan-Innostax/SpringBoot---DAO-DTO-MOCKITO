package com.innostax.employee.config;

import org.apache.tomcat.jdbc.pool.DataSource; // Tomcat DataSource for connection pooling
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;


import java.util.HashMap;
import java.util.Map;

@Configuration
public class HibernateConfig {

    // Injecting database and Hibernate properties from the application.properties file
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${spring.datasource.driver-class-name}")
    private String dbDriverClassName;


    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String hibernateDialect;

    @Value("${spring.jpa.properties.hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Value("${spring.jpa.properties.hibernate.show_sql}")
    private boolean showSql;

    @Value("${spring.jpa.properties.hibernate.format_sql}")
    private boolean formatSql;

    // Custom DataSource Bean using Apache Tomcat JDBC connection pool
    @Bean
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        dataSource.setDriverClassName(dbDriverClassName);
        return dataSource;
    }

    // Hibernate-specific configurations
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("com.innostax.employee.Entity");
        factoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter());

        // Set Hibernate properties
        Map<String, Object> properties = new HashMap<>();
        properties.put("spring.jpa.properties.hibernate.dialect", hibernateDialect);
        properties.put("spring.jpa.properties.hibernate.hbm2ddl.auto", hbm2ddlAuto);
        properties.put("spring.jpa.properties.hibernate.show_sql", showSql);
        properties.put("spring.jpa.properties.hibernate.format_sql", formatSql);
        
        factoryBean.setJpaPropertyMap(properties);

        return factoryBean;
    }

    // Hibernate Vendor Adapter
    @Bean
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(showSql);
        vendorAdapter.setGenerateDdl(false);  
        vendorAdapter.setDatabasePlatform(hibernateDialect); 
        return vendorAdapter;
    }

    // Define Transaction Manager
    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }
}
