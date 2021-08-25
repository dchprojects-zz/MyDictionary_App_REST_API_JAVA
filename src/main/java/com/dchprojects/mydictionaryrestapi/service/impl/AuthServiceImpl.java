package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.dto.*;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.service.AuthService;
import com.dchprojects.mydictionaryrestapi.service.JWTService;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JWTService jwtService;
    private final UserService userService;

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        try {

            UserEntity userEntity = userService.findByNickname(authRequest.getNickname());

            JWTInternalRequest jwtRequest = new JWTInternalRequest(userEntity.getUserId(),
                    userEntity.getNickname(),
                    userEntity.getPassword());

            return new AuthResponse(userEntity, jwtService.jwtResponse(jwtRequest));

        } catch (NoSuchElementException noSuchElementException) {
            throw new NoSuchElementException(noSuchElementException.getLocalizedMessage());
        }
    }

    @Override
    public AuthResponse register(CreateUserRequest createUserRequest) {
        try {

            UserEntity savedUser = userService.createUser(createUserRequest);

            JWTInternalRequest jwtRequest = new JWTInternalRequest(savedUser.getUserId(),
                    savedUser.getNickname(),
                    savedUser.getPassword());

            return new AuthResponse(savedUser, jwtService.jwtResponse(jwtRequest));
            
        } catch (ValidationException validationException) {
            throw new ValidationException(validationException.getLocalizedMessage());
        }
    }

}
