package com.imooc.security.securitycore.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BizpageController {


    @Value("${spring.security.loginType}")
    private String loginType;
    // 登录页面
    @GetMapping("/loginPage")
    public String loginpPage() {
        if ("JSON".equalsIgnoreCase(loginType)) {
            return "public/login_json";
        }else {
            return "public/login";
        }
    }

    // 登录成功之后的首页
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // 日志管理
    @GetMapping("/syslog")
    public String showOrder() {
        return "syslog";
    }

    // 用户管理
    @GetMapping("/sysuser")
    public String addOrder() {
        return "sysuser";
    }

    // 具体业务一
    @GetMapping("/biz1")
    public String updateOrder() {
        return "biz1";
    }

    // 具体业务二
    @GetMapping("/biz2")
    public String deleteOrder() {
        return "biz2";
    }
}