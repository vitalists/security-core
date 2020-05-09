package com.imooc.security.securitycore.config.codeGenerator.sms;

import com.imooc.security.securitycore.config.codeGenerator.dto.CodeDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SmsCodeGenerator {
    public String generatorCode() {
        String numeric = RandomStringUtils.randomNumeric(4);
        CodeDto codeDto = new CodeDto(numeric,60 * 10000);
        log.info("发送的手机验证码：{}", codeDto.getCode());
        return numeric;
    }
}
