package com.develop.jina1.config;

import com.querydsl.sql.H2Templates;
import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.spring.SpringExceptionTranslator;
import lombok.AllArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class CustomJdbcConfiguration extends AbstractJdbcConfiguration {

    private final JdbcProps jdbcProps;

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(jdbcProps.getJdbcDriver())
                .url(jdbcProps.getUrl())
                .username(jdbcProps.getUsername())
                .password(jdbcProps.getPassword())
                .build();
    }

    @Bean
//    @Profile("!test")
    com.querydsl.sql.Configuration querydslConfiguration() {
        SQLTemplates templates = MySQLTemplates.builder().build();
        com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(templates);
        configuration.setExceptionTranslator(new SpringExceptionTranslator());
        return configuration;
    }

//    @Bean
//    @Profile("test")
//    com.querydsl.sql.Configuration querydslTestConfiguration() {
//        SQLTemplates templates = H2Templates.builder().build();
//        com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(templates);
//        configuration.setExceptionTranslator(new SpringExceptionTranslator());
//        return configuration;
//    }

    @Bean
//    @Profile("!test")
    SQLQueryFactory sqlQueryFactory(DataSource dataSource) {
        return new SQLQueryFactory(querydslConfiguration(), new TransactionAwareDataSourceProxy(dataSource));
    }

//    @Bean
//    @Profile("test")
//    SQLQueryFactory testSqlQueryFactory(DataSource dataSource) {
//        return new SQLQueryFactory(querydslTestConfiguration(), new TransactionAwareDataSourceProxy(dataSource));
//    }
}
