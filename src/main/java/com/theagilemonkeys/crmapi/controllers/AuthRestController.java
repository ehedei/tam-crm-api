package com.theagilemonkeys.crmapi.controllers;

import com.theagilemonkeys.crmapi.models.UserEntity;
import com.theagilemonkeys.crmapi.security.jwt.JWTProvider;
import com.theagilemonkeys.crmapi.security.jwt.model.JWTUserResponse;
import com.theagilemonkeys.crmapi.security.jwt.model.LoginRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthRestController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JWTProvider jwtProvider;
    
    
    @PostMapping("/login")
    public ResponseEntity<JWTUserResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = this.authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(), 
                                loginRequest.getPassword()
                        ));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);     
        UserEntity user = (UserEntity) authentication.getPrincipal();
        String token = jwtProvider.generateToken(authentication);
 
        return ResponseEntity.status(HttpStatus.CREATED).body(new JWTUserResponse(user, token));
    }
    
    @GetMapping("/profile")
    public UserEntity getProfile(@AuthenticationPrincipal UserEntity user) {
        return user;
    }
}
