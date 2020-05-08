package com.imooc.security.securitycore.service;

import com.imooc.security.securitycore.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-05-08
 */
public interface ISysRoleService extends IService<SysRole> {
    List<String> getSysRoleListByUserId(Integer userId);
}
