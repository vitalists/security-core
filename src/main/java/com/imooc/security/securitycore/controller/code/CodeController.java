package com.imooc.security.securitycore.controller.code;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.imooc.security.securitycore.config.codeGenerator.dto.CodeDto;
import com.imooc.security.securitycore.config.codeGenerator.sms.SmsCodeGenerator;
import com.imooc.security.securitycore.entity.SysUser;
import com.imooc.security.securitycore.service.ISysUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@AllArgsConstructor
public class CodeController {
    private final DefaultKaptcha captchaProducer;

    private  final SmsCodeGenerator smsCodeGenerator;

    private final ISysUserService sysUserService;

    @RequestMapping("/kaptcha")
    public void imgGenerator(HttpServletResponse response, HttpSession session) {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();
        CodeDto imgCode = new CodeDto(capText, 1 * 60 * 1000);
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, imgCode);
        try (ServletOutputStream out = response.getOutputStream()) {
            BufferedImage bi = captchaProducer.createImage(capText);
            ImageIO.write(bi, "jpg", out);
            out.flush();
        }//
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/smscode")
    @ResponseBody
    public String smscode( String mobile , HttpServletResponse response, HttpSession session) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", mobile);
        SysUser user = sysUserService.getOne(queryWrapper);
        if (user == null) {
            return "手机号不存在";
        }
        String code = smsCodeGenerator.generatorCode();
        CodeDto imgCode = new CodeDto(code, 1 * 60 * 1000);
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, imgCode);
        return "send success";
    }
}
