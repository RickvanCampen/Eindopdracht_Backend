package com.example.eindopdracht_backend_ipmroved;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order
@EnableWebSecurity
public class TestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/fietsen/**").authenticated() // Bescherm de endpoints die je wilt testen
                .and()
                .httpBasic(); // Gebruik eenvoudige HTTP Basic-authenticatie voor testen
    }

    @Bean(name = "testAuthenticationManager")
    public AuthenticationManager testAuthenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean(name = "testPasswordEncoder")
    public PasswordEncoder testPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("testuser")
                .password(testPasswordEncoder().encode("testpassword"))
                .roles("USER");
    }
}
