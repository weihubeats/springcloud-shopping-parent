package com.zou.security.config;

import com.zou.security.mapper.PermissionMapper;
import com.zou.security.module.Permission;
import com.zou.security.service.MyUserDetailService;
import com.zou.security.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Security配置
 * @author wh
 * @version 1.0
 * @date 2019-12-25 11:51
 */
@Component
@EnableWebSecurity
@Slf4j
public class SpringSecurityConf extends WebSecurityConfigurerAdapter {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private MyUserDetailService myUserDetailService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(new PasswordEncoder() {

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                String encode = MD5Util.encode((String) rawPassword);
                encodedPassword=encodedPassword.replace("\r\n", "");
                boolean result = encodedPassword.equals(encode);
                return result;
            }
            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String) rawPassword);
            }
        });
    }


    /**
     *
     * @param http
     * @throws Exception
     *
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //查询所有权限,动态权限认证
        List<Permission> permissions = permissionMapper.findAllPermission();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http
                .authorizeRequests();
        permissions.forEach(permission ->
        {
            log.info("获取权限为" + permission.getPermTag());
            authorizeRequests.antMatchers(permission.getUrl()).hasAnyAuthority(permission.getPermTag());
        });
        authorizeRequests.
                antMatchers("/login").permitAll().// 登录跳转 URL 无需认证
                antMatchers("/**").
                fullyAuthenticated().
                and().
                formLogin()//表单认证
                .loginPage("/login").
                and().csrf().disable();
    }




}
