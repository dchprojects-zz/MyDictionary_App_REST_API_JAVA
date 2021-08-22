package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.dto.JWTRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.JWTResponse;

public interface JWTService {

    JWTResponse jwtResponse(JWTRequest jwtRequest);

}
