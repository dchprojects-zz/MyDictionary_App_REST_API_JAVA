package com.dchprojects.mydictionaryrestapi.controller;

import com.dchprojects.mydictionaryrestapi.entity.LanguageEntity;
import com.dchprojects.mydictionaryrestapi.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/languages")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping
    public List<LanguageEntity> list() { return languageService.listAll(); }

    @GetMapping("/{languageId}")
    public ResponseEntity<LanguageEntity> getLanguageById(@PathVariable Long languageId) {
        try {
            LanguageEntity language = languageService.findById(languageId);
            return new ResponseEntity<>(language, HttpStatus.OK);
        } catch (NoSuchElementException e) {
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
