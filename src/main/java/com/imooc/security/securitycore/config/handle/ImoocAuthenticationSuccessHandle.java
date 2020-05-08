package com.imooc.security.securitycore.config.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ImoocAuthenticationSuccessHandle extends SavedRequestAwareAuthenticationSuccessHandler {
    @Value("${spring.security.loginType:HTML}")
    private String loginType;

    private  static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        if ("JSON".equalsIgnoreCase(loginType)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString("success"));
        }else {
            // 默认跳转上一次请求的页面
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
