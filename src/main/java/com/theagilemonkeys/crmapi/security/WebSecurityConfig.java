package com.theagilemonkeys.crmapi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().anyRequest();
    }
        
    
    /*
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
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
        

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {   
        auth
                .inMemoryAuthentication()
                .withUser("ehedei")
                .password(this.passwordEncoder.encode("123456"))
                .roles("USER", "ADMIN");
        
        auth.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder);
    }*/
    
}
