package com.imooc.security.securitycore.config.codeGenerator.filter;

import com.google.code.kaptcha.Constants;
import com.imooc.security.securitycore.config.codeGenerator.exception.SessionAuthenticationException;
import com.imooc.security.securitycore.config.codeGenerator.exception.ValidateCode;
import com.imooc.security.securitycore.config.handle.ImoocAuthenticationFailureHandle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class CodeFilter extends OncePerRequestFilter {
    @Autowired
    ImoocAuthenticationFailureHandle imoocAuthenticationFailureHandle;
    private final String LOGIN_URL = "/login";
    private final String SMS_LOGIN = "/smslogin";
    private final String REQUEST_METHOD = "POST";
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (
                    (Objects.equals(LOGIN_URL, httpServletRequest.getRequestURI()) ||
                StringUtils.equalsIgnoreCase(SMS_LOGIN, httpServletRequest.getRequestURI()))
                        && REQUEST_METHOD.equalsIgnoreCase(httpServletRequest.getMethod())) {
            //1.验证谜底与用户输入是否匹配
            try {
                ValidateCode.validate(new ServletWebRequest(httpServletRequest), Constants.KAPTCHA_SESSION_KEY);
            } catch (SessionAuthenticationException e) {
                imoocAuthenticationFailureHandle.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
