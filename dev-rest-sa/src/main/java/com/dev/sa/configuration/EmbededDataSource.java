package com.dev.sa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;


/**
 * Created by Anie on 6/21/2016.
 */
@Configuration
@Profile("embeded")
public class EmbededDataSource extends AppConfig {


    @Override
    @Bean
    DataSource getDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:/hsql/schema.sql")
                .build();
        return db;
    }


}
