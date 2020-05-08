package com.imooc.security.securitycore.service.impl;

import com.imooc.security.securitycore.entity.SysUser;
import com.imooc.security.securitycore.mapper.SysUserMapper;
import com.imooc.security.securitycore.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-05-08
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
