package com.zou.security.service;

import com.zou.security.mapper.UserMapper;
import com.zou.security.module.Permission;
import com.zou.security.module.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WH
 * @version 1.0
 * @date 2019/12/26 20:01
 */
@Component
@Slf4j
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 认证  用户登入会先被拦截到这个页面
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        User user = userMapper.findByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            log.info("用户" + username +"不存在");
            throw new UsernameNotFoundException("用户不存在");
        }
        //查询用户权限
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Permission> permissions = userMapper.findPermissionByUsername(username);
        permissions.forEach(permission ->
                authorities.add(new SimpleGrantedAuthority(permission.getPermTag())));
        //设置用户权限
        user.setAuthorities(authorities);
        return user;


    }
}
