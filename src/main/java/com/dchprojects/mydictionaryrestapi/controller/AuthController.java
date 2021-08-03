package com.dchprojects.mydictionaryrestapi.controller;

import com.dchprojects.mydictionaryrestapi.configuration.security.JwtTokenUtil;
import com.dchprojects.mydictionaryrestapi.domain.dto.AuthRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.AuthResponse;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Authentication")
@RestController @RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getNickname(), request.getPassword()));

            User user = (User) authenticate.getPrincipal();

            if (userService.isExist(user.getUsername())) {
                UserEntity userEntity = userService.findByNickname(user.getUsername()).get();
                return ResponseEntity.ok(new AuthResponse(jwtTokenUtil.generateAccessToken(userEntity)));
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (BadCredentialsException ex) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

//    @PostMapping("/register")
//    public UserView register(@RequestBody @Valid CreateUserRequest request) {
//        return userService.create(request);
//    }


}
