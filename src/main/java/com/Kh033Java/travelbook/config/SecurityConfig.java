package com.Kh033Java.travelbook.config;

import com.Kh033Java.travelbook.security.Configurer;
import com.Kh033Java.travelbook.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;

    private static final String LOGIN_ENDPOINT = "/anonymous/login";
    private static final String REGISTRATION_ENDPOINT = "/anonymous/registration";
    private static final String UPLOAD_FILE_ENDPOINT = "/uploadFile";


    @Autowired
    public SecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(REGISTRATION_ENDPOINT).permitAll()
                .antMatchers(UPLOAD_FILE_ENDPOINT).permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new Configurer(tokenProvider));
    }
}
