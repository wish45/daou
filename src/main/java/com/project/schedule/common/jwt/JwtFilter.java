package com.project.schedule.common.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private TokenProvider tokenProvider;

    public JwtFilter(TokenProvider tokenProvider) {    //토큰 프로바이더를 주입받는다.
        this.tokenProvider = tokenProvider;
    }

    //jwt토큰의 인증정보를 SecurityContext에 저장하는 역할수행
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //tokenProvider.
        String jwt = resolveToken(httpServletRequest);    //리퀘스트에서 토큰을 받아서 jwt에 넣는다.
        String requestURI = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {//토큰 유효성 검증
            Authentication authentication = tokenProvider.getAuthentication(jwt);//토큰이 정상적이면 authentication객체를 받아옴.
            SecurityContextHolder.getContext().setAuthentication(authentication);    //SecurityContext에 저장
            logger.info("Security Context에 '{}' 인증 정보 저장, uri: {}", authentication.getName(), requestURI);
        } else {
            logger.info("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    //필터링을 하기위해서 토큰정보가 있어야 하니,
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);     //  request header에서 토큰 정보를 꺼내오는메서드

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }
}
