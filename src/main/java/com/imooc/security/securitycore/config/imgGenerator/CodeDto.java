package com.imooc.security.securitycore.config.imgGenerator;

import lombok.Data;

import java.io.Serializable;
@Data
public class CodeDto implements Serializable {
    private String code;

    private Long expireTime;

    public CodeDto(String code, Integer expireTime) {
        this.code = code;
        this.expireTime = System.currentTimeMillis() + expireTime;
    }


    public boolean isExpired() {
        return System.currentTimeMillis() > expireTime;
    }
}
