package org.donghyun.club.security.filter;

import lombok.extern.log4j.Log4j2;
import org.donghyun.club.security.util.JWTUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {

    private JWTUtil jwtUtil;

    public ApiLoginFilter(String defaultFilterProcessesUrl){
        super(defaultFilterProcessesUrl);
    }

    @Override // 특정 URL에서만 동작하게.
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

       log.info("ApiLoginFilter....................");

       String email = request.getParameter("email");
       String pw = "1111";

        if(email == null){
            throw new BadCredentialsException("Email Cannot be null");
        }
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(email, pw);

        return getAuthenticationManager().authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        //정상적인 토큰인지, 사용할 수 있는 토큰인지 검사
        log.info(authResult.getPrincipal());
        //super.successfulAuthentication(request, response, chain, authResult);
    }
}
