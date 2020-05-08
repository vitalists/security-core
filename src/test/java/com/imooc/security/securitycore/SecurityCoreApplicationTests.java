package com.imooc.security.securitycore;

import com.imooc.security.securitycore.entity.SysMenu;
import com.imooc.security.securitycore.mapper.SysMenuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class SecurityCoreApplicationTests {
    @Autowired
    SysMenuMapper sysMenuMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Test
    void contextLoads() {
        System.out.println(passwordEncoder.encode("admin"));
    }

}
