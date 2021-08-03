package com.dchprojects.mydictionaryrestapi.api;

import com.dchprojects.mydictionaryrestapi.domain.entity.LanguageEntity;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.RoleNameString;
import com.dchprojects.mydictionaryrestapi.service.LanguageService;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@Tag(name = "Admin")
@RestController
@RequestMapping("/api/v1/admin")
@RolesAllowed({RoleNameString.ROLE_ADMIN})
@RequiredArgsConstructor
public class AdminApi {

    private final UserService userService;
    private final LanguageService languageService;

    @GetMapping
    public List<UserEntity> list() {
        return userService.listAll();
    }

    @PostMapping
    public ResponseEntity<LanguageEntity> createLanguage(@RequestBody LanguageEntity language) {
        Boolean isExistLanguageName = languageService.isExist(language.getLanguageName());
        if (isExistLanguageName) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            languageService.save(language);
            Optional<LanguageEntity> savedLanguage = languageService.findByLanguageName(language.getLanguageName());
            if (savedLanguage.isPresent()) {
                return new ResponseEntity<>(savedLanguage.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

}
