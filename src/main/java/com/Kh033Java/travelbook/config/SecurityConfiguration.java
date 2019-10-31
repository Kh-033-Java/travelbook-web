package com.Kh033Java.travelbook.config;

import static java.util.Collections.singletonList;
import static org.springframework.web.cors.CorsConfiguration.ALL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.Kh033Java.travelbook.userDetails.Configurer;
import com.Kh033Java.travelbook.userDetails.TokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private final TokenProvider tokenProvider;

    @Autowired
    public SecurityConfiguration(UserDetailsService userDetailsService, TokenProvider tokenProvider) {
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
    }

    private final String LOGIN_END_POINT = "/anonymous/authentication";
    private final String SIMPLE_USER_END_POINT = "/users";
    private final String ADMIN_END_POINT = "/users/admin/**";
    private final String ANONYMOUS_END_POINT = "/anonymous";

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http

                .httpBasic().disable()
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(ANONYMOUS_END_POINT, LOGIN_END_POINT).permitAll()
                .antMatchers(SIMPLE_USER_END_POINT).hasAnyRole("Admin", "User")
                .antMatchers(ADMIN_END_POINT).hasRole("Admin")
                .and()
                .apply(new Configurer(tokenProvider))
                .and()
                .logout()
                .logoutSuccessUrl(ANONYMOUS_END_POINT)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        final List<String> allowAll = singletonList(ALL);
        configuration.setAllowedOrigins(allowAll);
        configuration.setAllowedMethods(allowAll);
        configuration.setAllowedHeaders(allowAll);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
