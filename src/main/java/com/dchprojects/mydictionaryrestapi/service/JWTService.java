package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.dto.JWTInternalRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.JWTResponse;
import com.dchprojects.mydictionaryrestapi.domain.dto.JWTApiRequest;

public interface JWTService {

    JWTResponse jwtResponse(JWTInternalRequest jwtRequest);

    JWTResponse jwtResponse(JWTApiRequest jwtApiRequest);

}
