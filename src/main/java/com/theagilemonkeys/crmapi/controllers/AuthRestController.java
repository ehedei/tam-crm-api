package com.theagilemonkeys.crmapi.controllers;

import com.theagilemonkeys.crmapi.models.UserEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/auth")
public class AuthRestController {
    @PostMapping("/login")
    public void login() {
    
    }
    
    @PostMapping("/profile")
    public UserEntity getProfile(@AuthenticationPrincipal UserEntity user) {
        return user;
    }
}
