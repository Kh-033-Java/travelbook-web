package com.Kh033Java.travelbook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration for Spring Security.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * Configuration with http security.
     * @param http http security
     * @throws Exception exception
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    /**
     * Configuration with authentication manager builder.
     * @param auth authentication manager builder
     * @throws Exception exception
     */
    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}password")
                .roles("USER");
    }
}
