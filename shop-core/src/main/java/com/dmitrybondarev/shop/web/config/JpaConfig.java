package com.dmitrybondarev.shop.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import java.util.Objects;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class JpaConfig {

    private Environment env;

    public JpaConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getEMF(DriverManagerDataSource dataSource,
                                                         JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource);
        bean.setJpaVendorAdapter(jpaVendorAdapter);
        bean.setJpaProperties(getHibernateProperties());
        bean.setPackagesToScan(env.getProperty("spring.jpa.entity_manager_factory.packages_to_scan"));
        bean.setPersistenceUnitName(env.getProperty("spring.jpa.entity_manager_factory.persistence_unit_name"));
        return bean;
    }

    @Bean
    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.dataSource.driver_class_name")));
        dataSource.setUrl(env.getProperty("spring.dataSource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter getHibernateAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public PlatformTransactionManager getTransactionManager(EntityManagerFactory emf){
        JpaTransactionManager jpaTransaction = new JpaTransactionManager();
        jpaTransaction.setEntityManagerFactory(emf);
        return jpaTransaction;
    }


// ============== NON-BEAN-INIT ============

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", Objects.requireNonNull(env.getProperty("spring.jpa.hibernate.dialect")));
        properties.put("hibernate.hbm2ddl.auto", Objects.requireNonNull(env.getProperty("spring.jpa.hibernate.hbm2ddl.auto")));
        properties.put("hibernate.show_sql", Objects.requireNonNull(env.getProperty("spring.jpa.hibernate.show_sql")));
        properties.put("hibernate.id.new_generator_mappings", Objects.requireNonNull(env.getProperty("spring.jpa.hibernate.id.new_generator_mappings")));
        properties.put("hibernate.format_sql", Objects.requireNonNull(env.getProperty("spring.jpa.hibernate.format_sql")));
        properties.put("logging.level.org.hibernate.SQL", Objects.requireNonNull(env.getProperty("logging.level.org.hibernate.SQL")));
        return properties;
    }
}
