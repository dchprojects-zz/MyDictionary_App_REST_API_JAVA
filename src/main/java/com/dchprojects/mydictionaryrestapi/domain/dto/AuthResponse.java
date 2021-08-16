package com.dchprojects.mydictionaryrestapi.domain.dto;

import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse {

    @JsonProperty("user_entity")
    private UserEntity userEntity;

    @JsonProperty("jwt")
    private JWTResponse jwtResponse;

    public AuthResponse(UserEntity userEntity,
                        JWTResponse jwtResponse) {

        this.userEntity = userEntity;
        this.jwtResponse = jwtResponse;

    }

    public UserEntity getUserEntity() { return userEntity; }

    public JWTResponse getJwtResponse() { return jwtResponse; }

}