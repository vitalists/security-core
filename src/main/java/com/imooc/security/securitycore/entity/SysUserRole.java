package com.imooc.security.securitycore.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户角色关系表
 * </p>
 *
 * @author jobob
 * @since 2020-05-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserRole extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色自增id
     */
    private Integer roleId;

    /**
     * 用户自增id
     */
    private Integer userId;


}
