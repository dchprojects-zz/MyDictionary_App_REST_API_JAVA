package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.configuration.security.JwtTokenUtil;
import com.dchprojects.mydictionaryrestapi.domain.dto.*;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.service.JWTService;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Override
    public JWTResponse jwtResponse(JWTInternalRequest jwtRequest) {

        JwtTokenResponse jwtTokenResponse = jwtTokenUtil.generateAccessToken(jwtRequest.getUserId(),
                jwtRequest.getNickname(),
                jwtRequest.getPassword());

        return new JWTResponse(jwtTokenResponse.getAccessToken(),
                jwtTokenResponse.getExpirationDate().toString());

    }

    @Override
    public JWTResponse jwtResponse(JWTApiRequest jwtApiRequest) {

        UserEntity userEntity = userService.findByNickname(jwtApiRequest.getNickname());

        JwtTokenResponse jwtTokenResponse = jwtTokenUtil.generateAccessToken(userEntity.getUserId(),
                userEntity.getNickname(),
                userEntity.getPassword());

        return new JWTResponse(jwtTokenResponse.getAccessToken(),
                jwtTokenResponse.getExpirationDate().toString());

    }

}
