package com.imooc.security.securitycore.config.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.imooc.security.securitycore.entity.SysUser;
import com.imooc.security.securitycore.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUser> wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        SysUser user = sysUserMapper.selectOne(wrapper);
        MyUserDetails myUserDetails = new MyUserDetails();
        myUserDetails.setPassword(user.getPassword());
        myUserDetails.setUsername(user.getUsername());
        myUserDetails.setAccountNonExpired(true);
        myUserDetails.setAccountNonLocked(true);
        myUserDetails.setCredentialsNonExpired(true);
        myUserDetails.setEnabled(true);
        myUserDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin"));
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return myUserDetails;
    }
}
