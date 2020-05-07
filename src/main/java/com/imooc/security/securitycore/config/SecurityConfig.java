package com.imooc.security.securitycore.config;

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
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .loginPage("/loginPage")
                .successForwardUrl("/index")
                .and()
                .authorizeRequests()
                // 登录页面放行
                .antMatchers("/login", "/login.html","/public/**","/loginPage").permitAll()
                // biz1 ,biz2 需要 user 或者admin 权限
                .antMatchers("/biz1", "biz2").hasAnyAuthority("ROLE_user", "ROLE_admin")
                // syslog , sysuser 需要 admin 角色
                .antMatchers("/syslog", "/sysuser").hasAnyRole("admin")
                .anyRequest().authenticated()
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers( "/css/**", "/fonts/**", "/img/**", "/js/**");
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
