package com.dchprojects.mydictionaryrestapi.api;

import com.dchprojects.mydictionaryrestapi.api.path.Path;
import com.dchprojects.mydictionaryrestapi.domain.dto.LanguageResponse;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.RoleNameString;
import com.dchprojects.mydictionaryrestapi.service.LanguageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.NoSuchElementException;

@Tag(name = "Language")
@RestController
@RequestMapping(Path.REQUEST_PATH_API_LANGUAGES)
@RolesAllowed({RoleNameString.ROLE_USER, RoleNameString.ROLE_ADMIN})
@RequiredArgsConstructor
public class LanguageApi {

    private final LanguageService languageService;

    private static final String REQUEST_PATH_API_LANGUAGES_INDIVIDUAL_LANGUAGE = "/{languageId}";

    @GetMapping
    public List<LanguageResponse> list() {
        return languageService.listAll();
    }

    @GetMapping(REQUEST_PATH_API_LANGUAGES_INDIVIDUAL_LANGUAGE)
    public ResponseEntity<LanguageResponse> getLanguageById(@PathVariable Long languageId) {
        try {
            return new ResponseEntity<>(languageService.findById(languageId), HttpStatus.OK);
        } catch (NoSuchElementException noSuchElementException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
