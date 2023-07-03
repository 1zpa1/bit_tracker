package com.zpa.bit_tracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/greeting/homepage", "/greeting/registration", "/greeting/createUser", "/",
                        "/login", "/greeting/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/greeting/login")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutSuccessUrl("/greeting/homepage")
                .and()
                .csrf().disable();
    }
}
