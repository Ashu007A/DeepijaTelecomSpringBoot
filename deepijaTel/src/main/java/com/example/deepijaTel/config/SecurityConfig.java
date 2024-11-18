package com.example.deepijaTel.config;

import com.example.deepijaTel.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers(
                    "/api/register",
                    "/api/login", "/api/**",
                    "/backgrounds.jpg", "/userbg.jpg", "/adminbg.jpg", "/styles.css", "/favicon.ico",
                    "/ConVox/dashboard.jpg", "/ConVox/logo_convox_login.png", "/ConVox/logo_convox_dashboard.png", "/ConVox/favicon_convox.ico", "/ConVox/styles_convox_login.css", "/ConVox/styles_convox_dashboard.css", "/ConVox/script_convox_dashboard.js", "/ConVox/script_convox_stations.js", "/ConVox/script_convox_servers.js",
                    "/register",
                    "/convox/login",
                    "/user_login",
                    "/admin/login",
                    "/dashboard",
                    "/convox/dashboard",
                    "/convox/logout",
                    "/convox/servers",
                    "/convox/stations",
                    "/static/**", "/resources/**", "/ConVox", "/js/**"
            ).permitAll()
            .anyRequest().authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}