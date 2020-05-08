package com.imooc.security.securitycore.mapper;

import com.imooc.security.securitycore.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-05-08
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    //根据userID查询用户角色
    @Select("SELECT role_code\n" +
            "FROM sys_role r\n" +
            "LEFT JOIN sys_user_role ur ON r.id = ur.role_id\n" +
            "LEFT JOIN sys_user u ON u.id = ur.user_id\n" +
            "WHERE u.id = #{userId}")
    List<String> findRoleByUserId(@Param("userId") Integer userId);
}
