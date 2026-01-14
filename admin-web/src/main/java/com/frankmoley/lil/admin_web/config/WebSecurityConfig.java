package com.frankmoley.lil.admin_web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
  @Autowired
  private DataSource dataSource;
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests((requests)-> requests
        .requestMatchers("/", "/index").permitAll()
        .anyRequest().authenticated()
      )
      .httpBasic(Customizer.withDefaults());
      return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return new JdbcUserDetailsManager(dataSource);
  }
}
