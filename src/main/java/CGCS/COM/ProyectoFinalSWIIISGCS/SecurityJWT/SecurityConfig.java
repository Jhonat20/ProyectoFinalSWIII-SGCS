package CGCS.COM.ProyectoFinalSWIIISGCS.SecurityJWT;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.AuthProvider;

/**
 * @file: SecurityConfig
 * @author: EdwarMoya
 * @created: 11/03/2024
 * @HoraCreated: 04:35 p. m.
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{


        return http
                .csrf((csrf->
                        csrf
                        .disable()))
                .authorizeHttpRequests(authRequest ->
                                authRequest
                                        .requestMatchers("/auth/**").permitAll()
                                        .anyRequest().authenticated()
                        )
                        .sessionManagement(sessionManager->
                                sessionManager
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
