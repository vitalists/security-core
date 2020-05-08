package com.imooc.security.securitycore.config.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.imooc.security.securitycore.entity.SysUser;
import com.imooc.security.securitycore.mapper.SysUserMapper;
import com.imooc.security.securitycore.service.ISysMenuService;
import com.imooc.security.securitycore.service.ISysRoleService;
import com.imooc.security.securitycore.service.ISysUserService;
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
import java.util.stream.Collectors;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Autowired
    private ISysUserService sysUserService;
    @Resource
    private ISysRoleService sysRoleService;
    @Resource
    private ISysMenuService iSysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUser> wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        SysUser user = sysUserService.getOne(wrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 获取角色
        List<String> listByUserId = sysRoleService.getSysRoleListByUserId(user.getId());
        if (CollectionUtils.isEmpty(listByUserId)) {
            throw new UsernameNotFoundException("用户角色不存在");
        }
        List<String> collect = listByUserId.stream().map(role -> "ROLE_" + role).collect(Collectors.toList());
        // 根据角色查按钮权限
        List<String> roleCodes = iSysMenuService.getAuthorityByRoleCodes(listByUserId);
        collect.addAll(roleCodes);

        MyUserDetails myUserDetails = new MyUserDetails();
        myUserDetails.setPassword(user.getPassword());
        myUserDetails.setUsername(user.getUsername());
        myUserDetails.setAccountNonExpired(true);
        myUserDetails.setAccountNonLocked(true);
        myUserDetails.setCredentialsNonExpired(true);
        myUserDetails.setEnabled(true);
        myUserDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",",roleCodes)));
        return myUserDetails;
    }
}
