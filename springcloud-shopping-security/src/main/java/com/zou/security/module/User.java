package com.zou.security.module;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author wh
 * @version 1.0
 * @date 2019-12-25 10:04
 */
@Data
public class User implements UserDetails {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 别名
     */
    private String realname;
    /**
     *密码
     */
    private String password;
    /**
     *创建时间
     */
    private Date createDate;
    /**
     *最后登入时间
     */
    private Date lastLoginTime;
    /**
     *
     */
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    /**
     * 用户所有权限
     */
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    /**
     * 封装了权限信息
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }





}
