package com.imooc.security.securitycore.service.impl;

import com.imooc.security.securitycore.entity.SysRole;
import com.imooc.security.securitycore.mapper.SysRoleMapper;
import com.imooc.security.securitycore.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-05-08
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public  List<String> getSysRoleListByUserId(Integer userId) {

        return sysRoleMapper.findRoleByUserId(userId);
    }
}
