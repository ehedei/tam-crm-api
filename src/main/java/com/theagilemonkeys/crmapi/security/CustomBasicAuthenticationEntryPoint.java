package com.theagilemonkeys.crmapi.security;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {        
        response.addHeader("WWW-Authenticate", "Basic realm=\"" + this.getRealmName() + "\"");
        response.setContentType("application/json");
        response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
        
    }
    
    @PostConstruct
    public void initRealmName() {
        this.setRealmName("theagilemonkeys.com");
    }
}
