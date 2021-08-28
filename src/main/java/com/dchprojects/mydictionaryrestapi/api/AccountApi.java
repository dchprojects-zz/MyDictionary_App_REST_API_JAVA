package com.dchprojects.mydictionaryrestapi.api;

import com.dchprojects.mydictionaryrestapi.api.path.Path;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.RoleNameString;
import com.dchprojects.mydictionaryrestapi.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.NoSuchElementException;

@Tag(name = "Account")
@RestController
@RequestMapping(Path.REQUEST_PATH_API_ACCOUNT)
@RolesAllowed({RoleNameString.ROLE_USER, RoleNameString.ROLE_ADMIN})
@RequiredArgsConstructor
public class AccountApi {

    private final AccountService accountService;

    private static final String REQUEST_PATH_API_DELETE_ACCOUNT_INDIVIDUAL_USER = "/deleteAccount/userId/{userId}";

    @DeleteMapping(REQUEST_PATH_API_DELETE_ACCOUNT_INDIVIDUAL_USER)
    public ResponseEntity<?> deleteAccount(@PathVariable Long userId) {
        try {
            accountService.deleteAccount(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException noSuchElementException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
