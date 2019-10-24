package com.Kh033Java.travelbook.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 
 * Class disables basic Spring Security service
 *
 */

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	 @Override
	    protected void configure(HttpSecurity httpSecurity) throws Exception {
	        httpSecurity.authorizeRequests().antMatchers("/").permitAll();
	}

}
