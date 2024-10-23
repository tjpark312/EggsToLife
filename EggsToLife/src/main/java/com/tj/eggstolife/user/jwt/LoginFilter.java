package com.tj.eggstolife.user.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Spring Security의 기본 로그인 처리 로직을 재사용하거나 확장을 하기 위해서 사용. 상속을 통해 커스터마이징 함
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public LoginFilter(AuthenticationManager authenticationManager) {

        this.authenticationManager = authenticationManager;

    }

    @Override // 클라이언트로부터 온 요청과 반환 할 응답임, Http 통신을 처리하기 위함이다.
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    // 1. 사용잘로부터 username 과 password 를 받음
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        System.out.println(username);
    // 2. 이 정보를 기반으로 토큰을 생성한다.
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);
    // 3. 적절한 authenticationProvider 에게 전달하여 인증을 요청한다 -> 인증을 authenticationManager가 인증을 담당할 것임.           
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication){

        System.out.println("successful authentication");
        
    }
    
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed){

        System.out.println("unsuccessful authentication");
        
    }
}
