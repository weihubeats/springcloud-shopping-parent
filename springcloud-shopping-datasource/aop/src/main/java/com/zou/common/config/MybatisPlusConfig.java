package com.zou.common.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zou.common.constans.Constant;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WH
 * @version 1.0
 * @date 2020/8/22 8:37
 * @Description TODO
 */
@EnableTransactionManagement
@Configuration
@MapperScan(basePackages ={"com.zou.dao.db0.mapper","com.zou.dao.db1.mapper"})
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

    @Bean(name = Constant.DB0)
    @ConfigurationProperties(prefix = "spring.datasource.druid.db0")
    public DataSource db0() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = Constant.DB1)
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid.db1")
    public DataSource db1() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源配置
     * @param db0
     * @param db1
     * @return javax.sql.DataSource
     * @author wh
     * @date 2020/7/21
     */
    @Bean
    public DataSource multipleDataSource(@Qualifier(Constant.DB0) DataSource db0,
                                         @Qualifier(Constant.DB1) DataSource db1) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(Constant.DB0, db0);
        targetDataSources.put(Constant.DB1, db1);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        // 设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(db0);
        return dynamicDataSource;
    }

    /**
     *  事务管理器
     * @param
     * @return DataSourceTransactionManager
     * @author wh
     * @date 2020/7/27
     */
    @Bean
    public DataSourceTransactionManager transactionManager () {
        return new DataSourceTransactionManager( multipleDataSource(db0(), db1()) );
    }

    @Bean
    @Primary
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean () throws Exception{
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        // 实体类别名
        bean.setTypeAliasesPackage("com.zou.model");
        MybatisConfiguration configuration = new MybatisConfiguration();
        //开启下划线转驼峰
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        bean.setDataSource(multipleDataSource(db0(), db1()));
        return bean;
    }

}
