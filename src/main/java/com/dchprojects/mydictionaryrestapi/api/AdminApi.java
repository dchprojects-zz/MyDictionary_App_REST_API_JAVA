package com.dchprojects.mydictionaryrestapi.api;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateLanguageRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.CreateUserRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.LanguageEntity;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
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
@RequestMapping("/api/v1/admin")
@RolesAllowed({RoleNameString.ROLE_ADMIN})
@RequiredArgsConstructor
public class AdminApi {

    private final AdminService adminService;

    @GetMapping("/users")
    public List<UserEntity> list() {
        return adminService.userList();
    }

    @PostMapping("/createLanguage")
    public ResponseEntity<LanguageEntity> createLanguage(@RequestBody @Valid CreateLanguageRequest createLanguageRequest) {
        try {
            return new ResponseEntity<>(adminService.createLanguage(createLanguageRequest), HttpStatus.OK);
        } catch (ValidationException validationException) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/register/admin")
    public ResponseEntity<UserEntity> registerAdmin(@RequestBody @Valid CreateUserRequest createUserRequest) {
        try {
            return new ResponseEntity<>(adminService.registerAdmin(createUserRequest), HttpStatus.OK);
        } catch (ValidationException validationException) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
