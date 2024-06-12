package com.example.eindopdracht_backend_ipmroved.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and() // CorsConfiguration toegevoegd
                .csrf().disable() // CSRF-bescherming uitgeschakeld
                .authorizeRequests()
                .antMatchers("/api/public/**").permitAll() // Openbare toegang
                .antMatchers("/api/private/**").authenticated() // Vereist authenticatie
                .antMatchers("/api/admin/**").hasRole("ADMIN") // Vereist ADMIN-rol
                .anyRequest().authenticated() // Vereist authenticatie voor andere URL's
                .and()
                .formLogin()
                .loginPage("/login") // Aangepaste inlogpagina
                .permitAll() // Iedereen kan de inlogpagina bezoeken
                .and()
                .logout()
                .permitAll(); // Iedereen kan uitloggen
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
