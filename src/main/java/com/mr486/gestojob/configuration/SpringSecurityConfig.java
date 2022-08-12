package com.mr486.gestojob.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
      .withUser("admin").password(passwordEncoder().encode("mdp12345")).roles("ADMIN", "USER");
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
      .csrf()
      .disable()
      .exceptionHandling()
      .authenticationEntryPoint(new Http403ForbiddenEntryPoint() {
      })
      .and()
      .formLogin()
      .loginPage("/login")
      .loginProcessingUrl("/login")
      .defaultSuccessUrl("/admin")
      .and()
      .logout()
      .logoutUrl("/logout")
      .logoutSuccessUrl("/")
      .invalidateHttpSession(true)
      .and()
      .authorizeRequests()
      .antMatchers("/admin").authenticated()
      .anyRequest().permitAll();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
