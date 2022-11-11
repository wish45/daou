package com.project.schedule.common.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//jwtfilter와 tokenprovier를 securityConfig에 적용할 클래스파일이다.
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private TokenProvider tokenProvider;

    public JwtSecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) {

        JwtFilter customFilter = new JwtFilter(tokenProvider); //jwtfiler를
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);//시큐리티 로직에 필터를 등록하는 역할
    }
}
