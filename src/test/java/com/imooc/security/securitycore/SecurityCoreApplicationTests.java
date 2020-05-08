package com.imooc.security.securitycore;

import com.imooc.security.securitycore.entity.SysMenu;
import com.imooc.security.securitycore.mapper.SysMenuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class SecurityCoreApplicationTests {
    @Autowired
    SysMenuMapper sysMenuMapper;
    @Test
    void contextLoads() {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setMenuPid(0);
        sysMenu.setMenuPids("");
        sysMenu.setIsLeaf(0);
        sysMenu.setMenuName("");
        sysMenu.setUrl("");
        sysMenu.setIcon("");
        sysMenu.setIconColor("");
        sysMenu.setSort(0);
        sysMenu.setLevel(0);
        sysMenu.setStatus(0);
        sysMenuMapper.insert(sysMenu);
    }

}
