package com.dchprojects.mydictionaryrestapi.api;

import com.dchprojects.mydictionaryrestapi.api.path.Path;
import com.dchprojects.mydictionaryrestapi.domain.dto.UserResponse;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.RoleNameString;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.NoSuchElementException;

@Tag(name = "User")
@RestController
@RequestMapping(Path.REQUEST_PATH_API_USERS)
@RolesAllowed({RoleNameString.ROLE_USER, RoleNameString.ROLE_ADMIN})
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    private static final String REQUEST_PATH_API_USERS_INDIVIDUAL_USER = "/{userId}";

    @GetMapping(REQUEST_PATH_API_USERS_INDIVIDUAL_USER)
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        try {
            return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
        } catch (NoSuchElementException noSuchElementException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
