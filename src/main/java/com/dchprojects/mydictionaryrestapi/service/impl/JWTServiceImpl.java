package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.configuration.security.JwtTokenUtil;
import com.dchprojects.mydictionaryrestapi.domain.dto.*;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.service.JWTService;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Override
    public JWTResponse jwtResponse(JWTRequest jwtRequest) {

        JwtTokenResponse jwtTokenResponse = jwtTokenUtil.generateAccessToken(jwtRequest.getUserId(),
                                                                             jwtRequest.getNickname(),
                                                                             jwtRequest.getPassword());

        return new JWTResponse(jwtTokenResponse.getAccessToken(),
                               jwtTokenResponse.getExpirationDate().toString());

    }

    @Override
    public JWTResponse jwtResponse(AccessTokenRequest accessTokenRequest) {
        try {

            UserEntity userEntity = userService.findByNickname(accessTokenRequest.getNickname());

            JwtTokenResponse jwtTokenResponse = jwtTokenUtil.generateAccessToken(userEntity.getUserId(),
                                                                                 accessTokenRequest.getNickname(),
                                                                                 accessTokenRequest.getPassword());

            return new JWTResponse(jwtTokenResponse.getAccessToken(),
                                   jwtTokenResponse.getExpirationDate().toString());

        } catch (NoSuchElementException noSuchElementException) {
            throw new NoSuchElementException(noSuchElementException.getLocalizedMessage());
        }
    }

}
