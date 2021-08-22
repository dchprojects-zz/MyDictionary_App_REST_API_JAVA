package com.dchprojects.mydictionaryrestapi.api;

import com.dchprojects.mydictionaryrestapi.domain.dto.AuthRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.AuthResponse;
import com.dchprojects.mydictionaryrestapi.domain.dto.CreateUserRequest;
import com.dchprojects.mydictionaryrestapi.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.NoSuchElementException;

@Tag(name = "Authentication")
@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest) {
        try {
            return new ResponseEntity<>(authService.login(authRequest), HttpStatus.OK);
        } catch (NoSuchElementException noSuchElementException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid CreateUserRequest createUserRequest) {
        try {
            return new ResponseEntity<>(authService.register(createUserRequest), HttpStatus.OK);
        } catch (ValidationException validationException) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
