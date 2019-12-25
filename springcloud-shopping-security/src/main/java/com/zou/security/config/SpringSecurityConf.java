package com.zou.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author wh
 * @version 1.0
 * @date 2019-12-25 11:51
 */
@Configuration
public class SpringSecurityConf extends WebSecurityConfigurerAdapter {


    /**
     *
     * @param http
     * @throws Exception
     * formLogin() 开启登录
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 表单登录
                // http.httpBasic() // HTTP Basic
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .and()
                .authorizeRequests() // 授权配置
                .antMatchers("/login.html").permitAll()
                .anyRequest()  // 所有请求都需要登入
                .authenticated()// 都需要认证
                .and().csrf().disable();

    }




}
