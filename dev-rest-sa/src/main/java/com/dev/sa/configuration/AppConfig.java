package com.dev.sa.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Sujit Agarwal
 */
@Configuration
@ComponentScan(basePackages = "com.dev.sa")
@PropertySource(value = {"classpath:db.properties"})
public abstract class AppConfig {

    @Autowired
    protected Environment environment;

  /* @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
       JdbcTemplate jdbc= new JdbcTemplate(dataSource);
      // jdbc.setDataSource(getDataSource());
       return jdbc;
    }*/

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }

  /*  @Bean
    public SessionFactory getSessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan(new String[]{"com.dev.sa.entity"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory.getObject();
    }*/

    abstract DataSource getDataSource();

}
