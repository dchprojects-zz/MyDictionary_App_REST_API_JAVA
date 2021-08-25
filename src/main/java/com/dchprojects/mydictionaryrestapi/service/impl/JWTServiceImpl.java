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
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Override
    public JWTResponse jwtResponse(JWTInternalRequest jwtRequest) {

        authenticate(jwtRequest.getNickname(), jwtRequest.getPasswordFromRequest());

        JwtTokenResponse jwtTokenResponse = jwtTokenUtil.generateAccessToken(jwtRequest.getUserId(),
                jwtRequest.getNickname(),
                jwtRequest.getDatabasePassword());

        return new JWTResponse(jwtTokenResponse.getAccessToken(),
                jwtTokenResponse.getExpirationDate().toString());

    }

    @Override
    public JWTResponse jwtResponse(JWTApiRequest jwtApiRequest) {

        if (jwtTokenUtil.validateWithoutCheckExpired(jwtApiRequest.getOldJWT())) {

            authenticate(jwtApiRequest.getNickname(), jwtApiRequest.getPassword());

            UserEntity userEntity = userService.findById(jwtApiRequest.getUserId());

            JwtTokenResponse jwtTokenResponse = jwtTokenUtil.generateAccessToken(userEntity.getUserId(),
                    userEntity.getNickname(),
                    userEntity.getPassword());

            return new JWTResponse(jwtTokenResponse.getAccessToken(),
                    jwtTokenResponse.getExpirationDate().toString());

        } else {
            throw new ValidationException("Invalid JWT");
        }

    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new DisabledException("INVALID_CREDENTIALS", e);
        }
    }

}
