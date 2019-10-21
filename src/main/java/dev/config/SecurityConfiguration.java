package dev.config;

import dev.userDetails.Configurer;
import dev.userDetails.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private final TokenProvider tokenProvider;

    public SecurityConfiguration(UserDetailsService userDetailsService, TokenProvider tokenProvider) {
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
    }

    private final String LOGIN_END_POINT = "/anonymous/login";
    private final String SIMPLE_USER_END_POINT = "/api/v1/users";
    private final String ADMIN_END_POINT = "/api/v1/users/admin/**";
    private final String ANONYMOUS_END_POINT = "/anonymous";

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http

                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers(ANONYMOUS_END_POINT , LOGIN_END_POINT).permitAll()
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
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

}
