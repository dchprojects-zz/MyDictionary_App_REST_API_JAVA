package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.configuration.security.JwtTokenUtil;
import com.dchprojects.mydictionaryrestapi.domain.dto.*;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.service.AuthService;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Override
    public AuthResponse login(AuthRequest request) {
        try {

            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getNickname(),
                            request.getPassword()));

            User user = (User) authenticate.getPrincipal();

            UserEntity userEntity = userService.findByNickname(user.getUsername());

            JwtTokenResponse jwtTokenResponse = jwtTokenUtil.generateAccessToken(userEntity);

            JWTResponse jwtResponse = new JWTResponse(jwtTokenResponse.getAccessToken(),
                    jwtTokenResponse.getExpirationDate().toString());

            return new AuthResponse(userEntity, jwtResponse);
            
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

            JwtTokenResponse jwtTokenResponse = jwtTokenUtil.generateAccessToken(savedUser);
            JWTResponse jwtResponse = new JWTResponse(jwtTokenResponse.getAccessToken(),
                    jwtTokenResponse.getExpirationDate().toString());

            return new AuthResponse(savedUser, jwtResponse);

        } catch (BadCredentialsException badCredentialsException) {
            throw new BadCredentialsException(badCredentialsException.getLocalizedMessage());
        } catch (ValidationException validationException) {
            throw new ValidationException(validationException.getLocalizedMessage());
        }
    }

}
