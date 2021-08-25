package com.dchprojects.mydictionaryrestapi.api;

import com.dchprojects.mydictionaryrestapi.domain.dto.JWTResponse;
import com.dchprojects.mydictionaryrestapi.service.JWTService;
import com.dchprojects.mydictionaryrestapi.domain.dto.JWTApiRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.NoSuchElementException;

@Tag(name = "JWT")
@RestController
@RequestMapping("/api/v1/jwt")
@RequiredArgsConstructor
public class JWTApi {

    private final JWTService jwtService;

    @PostMapping("/access_token")
    public ResponseEntity<JWTResponse> accessToken(@RequestBody @Valid JWTApiRequest jwtApiRequest) {
        try {
            return new ResponseEntity<>(jwtService.jwtResponse(jwtApiRequest), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ValidationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (DisabledException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
