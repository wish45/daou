package com.project.schedule.controller;

import com.project.schedule.common.jwt.JwtFilter;
import com.project.schedule.common.jwt.TokenProvider;
import com.project.schedule.dto.TokenDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.project.schedule.dto.LoginDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDTO> authorize(@Valid @RequestBody LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken =    //토큰객체 생성
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

        //authenticate메서드가 실행될때 customuserDetailsService에서 loadUserByUsername메서드가 실행됨.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);//생성된 객체를 SecurityContext에 저장하고 

        String jwt = tokenProvider.createToken(authentication);//인증정보를 기준으로 jwt토큰을 만든다.

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt); //reponseheader에 넣어주고

        return new ResponseEntity<>(new TokenDTO(jwt), httpHeaders, HttpStatus.OK);//responsebody에도 넣어서 리턴한다.
    }
}
