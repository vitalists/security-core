package com.imooc.security.securitycore.config.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.securitycore.config.imgGenerator.SessionAuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class ImoocAuthenticationFailureHandle extends SimpleUrlAuthenticationFailureHandler {
    @Value("${spring.security.loginType:HTML}")
    private String loginType;

    private  static ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if ("JSON".equalsIgnoreCase(loginType)) {
//            throw new RuntimeException("failure");
            response.getWriter().write(objectMapper.writeValueAsString("failure"));
            if (exception instanceof SessionAuthenticationException) {
                response.getWriter().write(objectMapper.writeValueAsString(exception.getMessage()));
            }
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
