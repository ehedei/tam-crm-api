package com.theagilemonkeys.crmapi.security.jwt.model;

import com.theagilemonkeys.crmapi.models.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class JWTUserResponse {
    private UserEntity user;
    private String token;
}
