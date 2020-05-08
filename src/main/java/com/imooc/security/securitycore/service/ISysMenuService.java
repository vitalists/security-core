package com.imooc.security.securitycore.service;

import com.imooc.security.securitycore.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-05-08
 */
public interface ISysMenuService extends IService<SysMenu> {
    List<String> getAuthorityByRoleCodes(List<String> roleCodes);
}
