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
    private static final String GET_ALL_USERS_ENDPOINT = "/users/allUsers";
    private static final String GET_ALL_CITIES_BY_COUNTRY_ENDPOINT = "/country/**/cities";
    private static final String GET_ALL_TRANSPORT_ENDPOINT = "/transport";
    private static final String GET_ALL_COUNTRY_PLANS_ENDPOINT = "/country/**/plans";
    private static final String GET_PLAN_ENDPOINT = "/country/plans/**";
    private static final String GET_NOTE_BY_ID_ENDPOINT = "/notes/**";
    private static final String GET_ALL_NOTES_ENDPOINT = "/country/**/notes";
    private static final String GET_GENERAL_INFORMATION_ENDPOINT = "/country/{countryName}/description"; //Doesn't work, Exception NullPointer
    private static final String GET_ALL_PUBLIC_PHOTOS = "/country/**/photos";
    private static final String GET_NOTES_BY_COUNTRY_BY_USER ="/country/**/notes/profile/**";
    private static final String GET_PLANES_BY_COUNTRY_BY_USER ="/country/**/plans/profile/**";
    private static final String GET_PHOTO_BY_COUNTRY_BY_USER ="/country/**/photos/profile/**";

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
                .antMatchers(LOGIN_ENDPOINT).permitAll()//todo works
                .antMatchers(REGISTRATION_ENDPOINT).permitAll()//todo works
                .antMatchers(UPLOAD_FILE_ENDPOINT).permitAll()//todo works
                .antMatchers(GET_ALL_USERS_ENDPOINT).permitAll()//todo works
                .antMatchers(GET_ALL_CITIES_BY_COUNTRY_ENDPOINT).permitAll()//todo works
                .antMatchers(GET_ALL_TRANSPORT_ENDPOINT).permitAll()//todo works
                .antMatchers(GET_ALL_COUNTRY_PLANS_ENDPOINT).permitAll()//todo works
                .antMatchers(GET_PLAN_ENDPOINT).permitAll()//todo works
                .antMatchers(GET_NOTE_BY_ID_ENDPOINT).permitAll()//todo works
                .antMatchers(GET_ALL_NOTES_ENDPOINT).permitAll()//todo works
                .antMatchers(GET_ALL_PUBLIC_PHOTOS).permitAll()//todo works
                .antMatchers(GET_NOTES_BY_COUNTRY_BY_USER).permitAll()//todo doesn't returns notes :(
                .antMatchers(GET_PLANES_BY_COUNTRY_BY_USER).permitAll()//todo works
                .antMatchers(GET_PHOTO_BY_COUNTRY_BY_USER).permitAll()//todo works
                .anyRequest().authenticated()
                .and()
                .apply(new Configurer(tokenProvider));
    }
}
