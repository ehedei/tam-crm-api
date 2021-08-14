package com.theagilemonkeys.crmapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    // A donde se puede acceder
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/auth/login")
                .permitAll()
                .antMatchers("/api/user/*")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated();        
    }
        
    // Qué usuario puede acceder
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {   
        auth
                .inMemoryAuthentication()
                .withUser("ehedei")
                .password(this.passwordEncoder.encode("123456"))
                .roles("USER", "ADMIN");
    }
}
