package com.imooc.security.securitycore.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统组织结构表
 * </p>
 *
 * @author jobob
 * @since 2020-05-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 上级组织编码
     */
    private Integer orgPid;

    /**
     * 所有的父节点id
     */
    private String orgPids;

    /**
     * 0:不是叶子节点，1:是叶子节点
     */
    private Integer isLeaf;

    /**
     * 组织名
     */
    private String orgName;

    /**
     * 地址
     */
    private String address;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮件
     */
    private String email;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 组织层级
     */
    private Integer level;

    /**
     * 0:启用,1:禁用
     */
    private Integer status;


}
