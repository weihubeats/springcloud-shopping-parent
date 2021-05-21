package com.zou.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;


import javax.sql.DataSource;

/**
 * @author : wh
 * @date : 2021/5/21 10:40
 * @description:
 */
@Data
@Configuration
@MapperScan(basePackages ={"com.zou.dao.mapper"},sqlSessionFactoryRef = "clickHouseSqlSessionFactoryBean")
@ConfigurationProperties(prefix = "clickhouse.jdbc.datasource")
public class ClickhouseConfig {

    private String username;

    private String driverClassName;

    private String password;

    private String url;

    private String type;

    private long maxWait;
    /**
     * 最小连接池数量
     */
    private int minIdle;
    /**
     * 最大连接数量
     */
    private int maxActive;




    @Bean
    public DataSource dataSource() throws Exception {
        Class classes = Class.forName(type);
        DruidDataSource dataSource = (DruidDataSource) DataSourceBuilder
                .create()
                .driverClassName(driverClassName)
                .type(classes)
                .url(url)
                .username(username)
                .password(password)
                .build();
        dataSource.setMaxWait(maxWait);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        // 设置druid 连接池非公平锁模式
        dataSource.setUseUnfairLock(true);
        return dataSource;
    }

    @Bean
    public MybatisSqlSessionFactoryBean clickHouseSqlSessionFactoryBean() throws Exception {
        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        factory.setDataSource(dataSource());
        factory.setTypeAliasesPackage("com.zou.dao.entity");
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setMapperLocations(resolver.getResources("classpath:clickhouse/*.xml"));
        //开启驼峰命名转换
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setJdbcTypeForNull(JdbcType.NULL);

        /*GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setBanner(false);
        factory.setGlobalConfig(globalConfig);*/
        factory.setConfiguration(configuration);
        return factory;
    }



}
