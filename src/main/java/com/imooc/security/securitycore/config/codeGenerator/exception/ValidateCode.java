package com.imooc.security.securitycore.config.codeGenerator.exception;

import com.google.code.kaptcha.Constants;
import com.imooc.security.securitycore.config.codeGenerator.dto.CodeDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class ValidateCode {
    public static void validate(ServletWebRequest request,String sessionKey) throws ServletRequestBindingException {
        HttpSession session = request.getRequest().getSession();

        // 登录请求中的验证码
        String codeInRequest = ServletRequestUtils.getStringParameter(
                request.getRequest(),"code");
        // session中的验证码
        CodeDto codeInSession = (CodeDto)
                session.getAttribute(sessionKey);

        if(Objects.isNull(codeInSession)) {
            throw new SessionAuthenticationException("您输入的验证码不存在");
        }

        // 校验服务器session池中的验证码是否过期
        if(codeInSession.isExpired()) {
            session.removeAttribute(sessionKey);
            throw new SessionAuthenticationException("验证码已经过期");
        }

        // 请求验证码校验
        if(!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new SessionAuthenticationException("验证码不匹配");
        }
    }
}
