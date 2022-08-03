package com.vnexpress.springbootproject.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
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
@EnableJpaRepositories(basePackages = {"com.vnexpress.springbootproject.repository.user"}
        , entityManagerFactoryRef = "userEMF"
        , transactionManagerRef = "userTM")
public class UserConfig {
    @Autowired(required = false)
    private PersistenceUnitManager persistenceUnitManager;
    Map<String, ?> jpaProperties = new HashMap<>();

    @Bean(name = "userDataSource")
    @ConfigurationProperties(prefix = "spring.user.datasource")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "userEMFB")
    public EntityManagerFactoryBuilder userEntityManagerFactoryBuilder() {

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.POSTGRESQL);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");

        return new EntityManagerFactoryBuilder(adapter, this.jpaProperties,
                this.persistenceUnitManager);
    }
    @Bean(name = "userEMF")
    public LocalContainerEntityManagerFactoryBean
    userEntityManagerFactory(
            @Qualifier("userDataSource") DataSource dataSource,
            @Qualifier("userEMFB") EntityManagerFactoryBuilder factoryBuilder) {

        Map<String, Object> vendorProperties = new HashMap<>();
        vendorProperties.put("hibernate.format_sql", true);
        vendorProperties.put("hibernate.jdbc.lob.non_contextual_creation", true);
        vendorProperties.put("hibernate.enable_lazy_load_no_trans", true);
//        vendorProperties.put("hibernate.default_schema", "USER");

        return factoryBuilder.dataSource(dataSource)
                .packages("com.vnexpress.springbootproject.entity.user")
                .properties(vendorProperties).build();
    }

    @Bean(name = "userTM")
    public PlatformTransactionManager userTransactionManager(
            @Qualifier("userEMF") EntityManagerFactory emf) {

        return new JpaTransactionManager(emf);
    }
}
