package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.dto.JWTRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.JWTResponse;
import com.dchprojects.mydictionaryrestapi.domain.dto.AccessTokenRequest;

public interface JWTService {

    JWTResponse jwtResponse(JWTRequest jwtRequest);

    JWTResponse jwtResponse(AccessTokenRequest accessTokenRequest);

}
