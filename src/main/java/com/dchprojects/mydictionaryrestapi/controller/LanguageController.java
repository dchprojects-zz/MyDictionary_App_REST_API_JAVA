package com.dchprojects.mydictionaryrestapi.controller;

import com.dchprojects.mydictionaryrestapi.domain.entity.LanguageEntity;
import com.dchprojects.mydictionaryrestapi.service.LanguageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Languages")
@RestController
@RequestMapping("/api/v1/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @GetMapping
    public List<LanguageEntity> list() { return languageService.listAll(); }

    @GetMapping("/{languageId}")
    public ResponseEntity<LanguageEntity> getLanguageById(@PathVariable Long languageId) {
        Boolean languageIsExist = languageService.isExist(languageId);
        if (languageIsExist) {
            return new ResponseEntity<>(languageService.findById(languageId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
