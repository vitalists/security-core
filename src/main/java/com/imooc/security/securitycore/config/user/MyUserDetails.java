package com.imooc.security.securitycore.config.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class MyUserDetails implements UserDetails {
    //密码
    String password;
    //用户名
    String username;
    //是否没过期
    boolean accountNonExpired;
    //是否没被锁定
    boolean accountNonLocked;
    //是否没过期
    boolean credentialsNonExpired;
    //账号是否可用
    boolean enabled;
    //用户的权限集合
    Collection<? extends GrantedAuthority> authorities;
}
