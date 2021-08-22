package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.dto.*;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.service.AuthService;
import com.dchprojects.mydictionaryrestapi.service.JWTService;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
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

            JWTRequest jwtRequest = new JWTRequest(userEntity.getUserId(),
                    userEntity.getNickname(),
                    userEntity.getPassword());

            return new AuthResponse(userEntity, jwtService.jwtResponse(jwtRequest));
            
        } catch (BadCredentialsException badCredentialsException) {
            throw new BadCredentialsException(badCredentialsException.getLocalizedMessage());
        } catch (NoSuchElementException noSuchElementException) {
            throw new NoSuchElementException(noSuchElementException.getLocalizedMessage());
        }
    }

    @Override
    public AuthResponse register(CreateUserRequest createUserRequest) {
        try {

            UserEntity savedUser = userService.createUser(createUserRequest);

            JWTRequest jwtRequest = new JWTRequest(savedUser.getUserId(),
                    savedUser.getNickname(),
                    savedUser.getPassword());

            return new AuthResponse(savedUser, jwtService.jwtResponse(jwtRequest));

        } catch (BadCredentialsException badCredentialsException) {
            throw new BadCredentialsException(badCredentialsException.getLocalizedMessage());
        } catch (ValidationException validationException) {
            throw new ValidationException(validationException.getLocalizedMessage());
        }
    }

}
