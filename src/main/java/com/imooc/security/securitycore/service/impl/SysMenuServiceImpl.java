package com.imooc.security.securitycore.service.impl;

import com.imooc.security.securitycore.entity.SysMenu;
import com.imooc.security.securitycore.mapper.SysMenuMapper;
import com.imooc.security.securitycore.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-05-08
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    @Autowired
    SysMenuMapper sysMenuMapper;
    @Override
    public List<String> getAuthorityByRoleCodes(List<String> roleCodes) {
        return sysMenuMapper.findAuthorityByRoleCodes(roleCodes);
    }
}
