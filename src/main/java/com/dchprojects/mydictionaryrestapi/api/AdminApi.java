package com.dchprojects.mydictionaryrestapi.api;

import com.dchprojects.mydictionaryrestapi.api.path.Path;
import com.dchprojects.mydictionaryrestapi.domain.dto.CreateLanguageRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.CreateUserRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.LanguageResponse;
import com.dchprojects.mydictionaryrestapi.domain.dto.UserResponse;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.RoleNameString;
import com.dchprojects.mydictionaryrestapi.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@Tag(name = "Admin")
@RestController
@RequestMapping(Path.REQUEST_PATH_API_ADMIN)
@RolesAllowed({RoleNameString.ROLE_ADMIN})
@RequiredArgsConstructor
public class AdminApi {

    private final AdminService adminService;

    private static final String REQUEST_PATH_API_USERS = "/users";
    private static final String REQUEST_PATH_API_CREATE_LANGUAGE = "/createLanguage";
    private static final String REQUEST_PATH_API_REGISTER_ADMIN = "/register/admin";

    @GetMapping(REQUEST_PATH_API_USERS)
    public List<UserResponse> list() {
        return adminService.userList();
    }

    @PostMapping(REQUEST_PATH_API_CREATE_LANGUAGE)
    public ResponseEntity<LanguageResponse> createLanguage(@RequestBody @Valid CreateLanguageRequest createLanguageRequest) {
        try {
            return new ResponseEntity<>(adminService.createLanguage(createLanguageRequest), HttpStatus.OK);
        } catch (ValidationException validationException) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping(REQUEST_PATH_API_REGISTER_ADMIN)
    public ResponseEntity<UserResponse> registerAdmin(@RequestBody @Valid CreateUserRequest createUserRequest) {
        try {
            return new ResponseEntity<>(adminService.registerAdmin(createUserRequest), HttpStatus.OK);
        } catch (ValidationException validationException) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
