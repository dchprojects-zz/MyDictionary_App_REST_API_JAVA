package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.configuration.security.JwtTokenUtil;
import com.dchprojects.mydictionaryrestapi.domain.dto.*;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.service.JWTService;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Override
    public JWTResponse jwtResponse(JWTInternalRequest jwtRequest) {

        authenticate(jwtRequest.getNickname(), jwtRequest.getPassword());

        JwtTokenResponse jwtTokenResponse = jwtTokenUtil.generateAccessToken(jwtRequest.getUserId(),
                jwtRequest.getNickname(),
                jwtRequest.getPassword());

        return new JWTResponse(jwtTokenResponse.getAccessToken(),
                jwtTokenResponse.getExpirationDate().toString());

    }

    @Override
    public JWTResponse jwtResponse(JWTApiRequest jwtApiRequest) {

        authenticate(jwtApiRequest.getNickname(), jwtApiRequest.getPassword());

        UserEntity userEntity = userService.findByNickname(jwtApiRequest.getNickname());

        JwtTokenResponse jwtTokenResponse = jwtTokenUtil.generateAccessToken(userEntity.getUserId(),
                jwtApiRequest.getNickname(),
                jwtApiRequest.getPassword());

        return new JWTResponse(jwtTokenResponse.getAccessToken(),
                jwtTokenResponse.getExpirationDate().toString());

    }

    private void authenticate(String nickname, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(nickname, password));
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }

}
