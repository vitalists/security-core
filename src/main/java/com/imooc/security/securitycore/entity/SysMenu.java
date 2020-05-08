package com.imooc.security.securitycore.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统菜单表
 * </p>
 *
 * @author jobob
 * @since 2020-05-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysMenu extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 父菜单ID
     */
    private Integer menuPid;

    /**
     * 当前菜单所有父菜单
     */
    private String menuPids;

    /**
     * 0:不是叶子节点，1:是叶子节点
     */
    private Integer isLeaf;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 跳转URL
     */
    private String url;

    private String icon;

    private String iconColor;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单层级
     */
    private Integer level;

    /**
     * 0:启用,1:禁用
     */
    private Integer status;


}
