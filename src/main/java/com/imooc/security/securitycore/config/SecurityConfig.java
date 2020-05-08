package com.imooc.security.securitycore.config;

import com.imooc.security.securitycore.config.handle.ImoocAuthenticationFailureHandle;
import com.imooc.security.securitycore.config.handle.ImoocAuthenticationSuccessHandle;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
@Component
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // 构造器注入
    private final ImoocAuthenticationFailureHandle imoocAuthenticationFailureHandle;
    private final ImoocAuthenticationSuccessHandle imoocAuthenticationSuccessHandle;
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                // 默认自带的登录页面
                .loginPage("/loginPage")
                // 登录处理url不写默认就是 "loginPage()方法中的url"
                .loginProcessingUrl("/login")
                .successHandler(imoocAuthenticationSuccessHandle)
                .failureHandler(imoocAuthenticationFailureHandle)
                .and()
                .authorizeRequests()
                // 登录页面放行
                .antMatchers("/login", "/login.html", "/public/**", "/loginPage").permitAll()
                // biz1 ,biz2 需要 user 或者admin 权限
                .antMatchers("/biz1", "biz2").hasAnyAuthority("ROLE_user", "ROLE_admin")
                // syslog , sysuser 需要 admin 角色
                .antMatchers("/syslog", "/sysuser").hasAnyRole("admin")
                .anyRequest().authenticated()
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/fonts/**", "/img/**", "/js/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user")
                .password(new BCryptPasswordEncoder().encode("user"))
                .roles("user")
                .and()
                .withUser("admin")
                .password(new BCryptPasswordEncoder().encode("admin"))
                .roles("admin")
                .and()
                .passwordEncoder(passwordEncoder());
    }
}
