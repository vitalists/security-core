package com.imooc.security.securitycore.mapper;

import com.imooc.security.securitycore.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 系统菜单表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-05-08
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    //根据用户角色查询用户权限
    @Select({
            "<script>",
            "SELECT url " ,
            "FROM sys_menu m " ,
            "LEFT JOIN sys_role_menu rm ON m.id = rm.menu_id " ,
            "LEFT JOIN sys_role r ON r.id = rm.role_id ",
            "WHERE r.role_code IN ",
            "<foreach collection='roleCodes' item='roleCode' open='(' separator=',' close=')'>",
            "#{roleCode}",
            "</foreach>",
            "</script>"
    })
    List<String> findAuthorityByRoleCodes(@Param("roleCodes") List<String> roleCodes);
}
