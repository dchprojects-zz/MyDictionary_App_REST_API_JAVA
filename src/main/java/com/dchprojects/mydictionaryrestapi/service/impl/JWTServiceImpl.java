package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.configuration.security.JwtTokenUtil;
import com.dchprojects.mydictionaryrestapi.domain.dto.*;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.service.JWTService;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Override
    public JWTResponse jwtResponse(JWTRequest jwtRequest) {
        try {

            JwtTokenResponse jwtTokenResponse = jwtTokenUtil.generateAccessToken(jwtRequest.getUserId(),
                    jwtRequest.getNickname());

            return new JWTResponse(jwtTokenResponse.getAccessToken(),
                    jwtTokenResponse.getExpirationDate().toString());

        } catch (BadCredentialsException badCredentialsException) {
            throw new BadCredentialsException(badCredentialsException.getLocalizedMessage());
        }

    }

    @Override
    public JWTResponse jwtResponse(AccessTokenRequest accessTokenRequest) {
        try {

            UserEntity userEntity = userService.findByNickname(accessTokenRequest.getNickname());

            JwtTokenResponse jwtTokenResponse = jwtTokenUtil.generateAccessToken(userEntity.getUserId(),
                    userEntity.getNickname());

            return new JWTResponse(jwtTokenResponse.getAccessToken(),
                    jwtTokenResponse.getExpirationDate().toString());

        } catch (BadCredentialsException badCredentialsException) {
            throw new BadCredentialsException(badCredentialsException.getLocalizedMessage());
        } catch (NoSuchElementException noSuchElementException) {
            throw new NoSuchElementException(noSuchElementException.getLocalizedMessage());
        }
    }

}
