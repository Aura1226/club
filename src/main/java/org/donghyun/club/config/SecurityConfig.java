package org.donghyun.club.config;

import lombok.extern.log4j.Log4j2;
import org.donghyun.club.security.filter.ApiCheckFilter;
import org.donghyun.club.security.filter.ApiLoginFilter;
import org.donghyun.club.security.handler.ApiLoginFailHandler;
import org.donghyun.club.security.handler.ClubLoginSuccessHandler;
import org.donghyun.club.security.util.JWTUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws  Exception{

        http.authorizeRequests()
                .antMatchers("/sample/all").permitAll()
                .antMatchers("/sample/member").hasRole("USER");

        http.formLogin();
        http.csrf().disable(); //왜 비활성화 시키는지...오후 12시 26분
        http.logout();

        http.oauth2Login().successHandler(successHandler());

        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

//    //인증 매니저 역할... 메모리상에서 불러오는거...
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user1")
//                .password("$2a$10$xEsvTe0lkXoVM8s38ziYquxacz7bfTYP7HCIzu0XfT9U4.cbsz9HK")
//                .roles("USER");//roles는 role_생략 가능
//    }


    @Bean
    public ClubLoginSuccessHandler successHandler(){
        return new ClubLoginSuccessHandler(passwordEncoder());
    }

    @Bean
    public ApiLoginFilter apiLoginFilter() throws Exception{

        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/api/login");

        apiLoginFilter.setAuthenticationManager(authenticationManager());
        apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());

        return apiLoginFilter;
    }

    @Bean
    public ApiCheckFilter apiCheckFilter(){
        return new ApiCheckFilter("/notes/**/*");
    }

    @Bean
    public JWTUtil jwtUtil(){
        return new JWTUtil();
    }

}
