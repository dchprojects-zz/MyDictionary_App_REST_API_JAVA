package com.dchprojects.mydictionaryrestapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse {

    @JsonProperty("user_response")
    private UserResponse userResponse;

    @JsonProperty("jwt")
    private JWTResponse jwtResponse;

    public AuthResponse(UserResponse userResponse,
                        JWTResponse jwtResponse) {

        this.userResponse = userResponse;
        this.jwtResponse = jwtResponse;

    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public JWTResponse getJwtResponse() { return jwtResponse; }

}