package com.example.edu.security;

import com.example.edu.repository.UserRepository;
import com.example.edu.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  private final UserRepository userRepository;

  public SecurityConfig(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> userRepository.findByUsername(username)
        .map(user -> org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            .roles("USER")
            .build())
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  @Bean
  public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
                                                     PasswordEncoder passwordEncoder) {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder);
    return new ProviderManager(authProvider);
  }

  @Bean
  public JwtAuthFilter jwtAuthFilter() {
    return new JwtAuthFilter();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                 JwtAuthFilter jwtAuthFilter) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/index.html", "/auth/**", "/css/**", "/js/**", "/images/**", "/static/**").permitAll()
            .anyRequest().authenticated()
        )
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .csrf(csrf -> csrf.disable())
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


    return http.build();
  }
}