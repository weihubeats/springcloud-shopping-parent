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

    private Integer id;
    private String username;
    private String realname;
    private String password;
    private Date createDate;
    private Date lastLoginTime;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    /**
     * 用户所有权限
     */
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }





}
