package com.mybatis.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.mybatis.dao"})
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource dataSource() {
        DataSource dataSource = DruidDataSourceBuilder.create().build();
        return dataSource;
    }
}
