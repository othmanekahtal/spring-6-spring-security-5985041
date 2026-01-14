package com.frankmoley.lil.admin_web.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests((requests)-> requests
        .requestMatchers("/", "/index").permitAll()
              .requestMatchers("/customers/**").hasRole("USER")
              .requestMatchers("/orders/**").hasRole("ADMIN")
        .anyRequest().authenticated()
      )
      .httpBasic(Customizer.withDefaults());
      return http.build();
  }
  @Bean
  public GrantedAuthoritiesMapper authoritiesMapper() {
    SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
    // Converts "user" from DB to "ROLE_USER" internally
    authorityMapper.setConvertToUpperCase(true);
    // Optional: Sets a default role if none found
    return authorityMapper;
  }

  @Bean
  public UserDetailsService userDetailsService(DataSource dataSource){
    return new JdbcUserDetailsManager(dataSource);
  }

}
