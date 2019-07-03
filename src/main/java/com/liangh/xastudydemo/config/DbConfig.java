package com.liangh.xastudydemo.config;

import bitronix.tm.resource.jdbc.PoolingDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    @Bean(name = "primaryMySqlDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.jta.bitronix.primary-datasource.datasource")
    public DataSource primaryMySqlDataSource() {
        PoolingDataSource bitronixDataSourceBean = new PoolingDataSource();
        return bitronixDataSourceBean;
    }


    @Bean(name = "secondaryMySqlDataSource")
    @ConfigurationProperties(prefix = "spring.jta.bitronix.secondary-datasource.datasource")
    public DataSource secondaryMySqlDataSource() {
        return new PoolingDataSource();
    }

    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("primaryMySqlDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "secondaryJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(
            @Qualifier("secondaryMySqlDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }



}
