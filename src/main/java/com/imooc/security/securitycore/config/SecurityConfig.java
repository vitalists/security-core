package com.imooc.security.securitycore.config;

import com.imooc.security.securitycore.config.handle.ImoocAuthenticationFailureHandle;
import com.imooc.security.securitycore.config.handle.ImoocAuthenticationSuccessHandle;
import com.imooc.security.securitycore.config.user.MyUserDetailsService;
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
    private  final MyUserDetailsService userDetailsService;
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
                .anyRequest().access("@rbacService.hasPermission(request,authentication)")
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/fonts/**", "/img/**", "/js/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

    }
}
