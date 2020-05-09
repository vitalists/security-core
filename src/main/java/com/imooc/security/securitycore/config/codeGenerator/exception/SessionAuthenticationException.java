package com.imooc.security.securitycore.config.codeGenerator.exception;

import org.springframework.security.core.AuthenticationException;

public class SessionAuthenticationException extends AuthenticationException {

    public SessionAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public SessionAuthenticationException(String msg) {
        super(msg);
    }
}
