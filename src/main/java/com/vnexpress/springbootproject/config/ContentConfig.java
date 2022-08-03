package com.vnexpress.springbootproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.vnexpress.springbootproject.repository.content"}
        , entityManagerFactoryRef = "contentEMF"
        , transactionManagerRef = "contentTM")
public class ContentConfig {

    @Autowired(required = false)
    private PersistenceUnitManager persistenceUnitManager;
    Map<String, ?> jpaProperties = new HashMap<>();

    @Bean(name = "contentDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "contentEMFB")
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.POSTGRESQL);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");

        return new EntityManagerFactoryBuilder(adapter, this.jpaProperties,
                this.persistenceUnitManager);
    }
    @Bean(name = "contentEMF")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
            @Qualifier("contentDataSource") DataSource dataSource,
            @Qualifier("contentEMFB") EntityManagerFactoryBuilder factoryBuilder) {

        Map<String, Object> vendorProperties = new HashMap<>();
        vendorProperties.put("hibernate.format_sql", true);
        vendorProperties.put("hibernate.jdbc.lob.non_contextual_creation", true);
        vendorProperties.put("hibernate.enable_lazy_load_no_trans", true);
//        vendorProperties.put("hibernate.default_schema", "content");

        return factoryBuilder.dataSource(dataSource)
                .packages("com.vnexpress.springbootproject.entity.content")
                .properties(vendorProperties).build();
    }

    @Bean(name = "contentTM")
    public PlatformTransactionManager transactionManager(
            @Qualifier("contentEMF") EntityManagerFactory emf) {

        return new JpaTransactionManager(emf);
    }

}

