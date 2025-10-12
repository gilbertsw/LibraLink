package id.co.libralink.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "id.co.libralink")
@EnableJpaRepositories(
        basePackages = "id.co.libralink",
        entityManagerFactoryRef = "mainEntityManager",
        transactionManagerRef = "mainTransactionManager"
)
public class DataSourceConfiguration {

    private Map<String,?> additionalJpaProperties(){
        Map<String,String> map = new HashMap<>();
        map.put("hibernate.hbm2ddl.auto", "update");
        map.put("hibernate.dialect", "org.hibernate.dialect.MariaDB106Dialect");
        map.put("hibernate.show_sql", "true");
        map.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
        return map;
    }

    @Primary
    @Bean(name = "mainEntityManager")
    public LocalContainerEntityManagerFactoryBean mainEntityManager(@Qualifier("mainDataSource") DataSource dataSource){
        LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();
        emFactory.setDataSource(dataSource);
        emFactory.setPackagesToScan("id.co.libralink");
        emFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emFactory.setJpaPropertyMap(additionalJpaProperties());
        return emFactory;
    }

    @Primary
    @Bean("mainDataSourceProperties")
    @ConfigurationProperties("spring.datasource.main")
    public DataSourceProperties mainDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Primary
    @Bean("mainDataSource")
    public HikariDataSource mainDataSource(@Qualifier("mainDataSourceProperties") DataSourceProperties properties) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getUrl());
        config.setUsername(properties.getUsername());
        config.setPassword(properties.getPassword());
        config.setPoolName("libraLinkDSPool");
        config.setMaximumPoolSize(4);
        config.setConnectionTimeout(1000);
        return new HikariDataSource(config);
    }

    @Primary
    @Bean(name = "mainTransactionManager")
    public JpaTransactionManager mainTransactionManager(
            @Qualifier("mainEntityManager") LocalContainerEntityManagerFactoryBean emFactory){
        return new JpaTransactionManager(Objects.requireNonNull(emFactory.getObject()));
    }

    @Bean(name = "mainDataSourceInitializer")
    public DataSourceInitializer mainDataSourceInitializer(@Qualifier("mainDataSource") DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("scripts/init-data.sql"));
        resourceDatabasePopulator.setContinueOnError(true);

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }

}
