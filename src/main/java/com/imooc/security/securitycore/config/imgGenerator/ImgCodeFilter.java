package com.imooc.security.securitycore.config.imgGenerator;

import com.google.code.kaptcha.Constants;
import com.imooc.security.securitycore.config.handle.ImoocAuthenticationFailureHandle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Component
public class ImgCodeFilter extends OncePerRequestFilter {
    @Autowired
    ImoocAuthenticationFailureHandle imoocAuthenticationFailureHandle;
    private final String LOGIN_URL = "/login";
    private final String REQUEST_METHOD = "POST";
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (Objects.equals(LOGIN_URL, httpServletRequest.getRequestURI()) && REQUEST_METHOD.equalsIgnoreCase(httpServletRequest.getMethod())) {
            //1.验证谜底与用户输入是否匹配
            try {
                validate(new ServletWebRequest(httpServletRequest));
            } catch (SessionAuthenticationException e) {
                imoocAuthenticationFailureHandle.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void validate(ServletWebRequest request) throws SessionAuthenticationException {
        HttpSession session = request.getRequest().getSession();
        //获取用户登录界面输入的captchaCode
        try {
            String codeInRequest = ServletRequestUtils.getStringParameter(
                    request.getRequest(),"imgcode");
            // 获取session池中的验证码谜底
            CodeDto codeInSession = (CodeDto)
                    session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if(Objects.isNull(codeInSession)) {
                throw new SessionAuthenticationException("您输入的验证码不存在");
            }

            // 校验服务器session池中的验证码是否过期
            if(codeInSession.isExpired()) {
                session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
                throw new SessionAuthenticationException("验证码已经过期");
            }

            // 请求验证码校验
            if(!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
                throw new SessionAuthenticationException("验证码不匹配");
            }
        } catch (ServletRequestBindingException e) {
            e.printStackTrace();
        }

    }
}
