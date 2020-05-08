package com.imooc.security.securitycore.controller.imgcode;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.imooc.security.securitycore.config.imgGenerator.CodeDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@AllArgsConstructor
public class ImgCodeContainer {
    private final DefaultKaptcha captchaProducer;

    @RequestMapping("/kaptcha")
    public void imgGenerator(HttpServletResponse response, HttpSession session) {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();
        CodeDto imgCode = new CodeDto(capText,1 *60* 1000);
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, imgCode);
        try(ServletOutputStream out = response.getOutputStream()) {
            BufferedImage bi = captchaProducer.createImage(capText);
            ImageIO.write(bi, "jpg", out);
            out.flush();
        }//
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
