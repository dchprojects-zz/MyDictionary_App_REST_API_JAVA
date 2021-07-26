package com.dchprojects.mydictionaryrestapi.controller;

import com.dchprojects.mydictionaryrestapi.entity.WordEntity;
import com.dchprojects.mydictionaryrestapi.service.CourseService;
import com.dchprojects.mydictionaryrestapi.service.LanguageService;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import com.dchprojects.mydictionaryrestapi.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/words")
public class WordController {

    @Autowired
    private WordService wordService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private LanguageService languageService;

    @GetMapping
    public List<WordEntity> list() {
        return wordService.listAll();
    }

    @PostMapping
    public ResponseEntity<WordEntity> createWord(@RequestBody WordEntity word) {
        Boolean userIsExist = userService.isExist(word.getUserId());
        Boolean courseIsExist = courseService.isExist(word.getUserId(), word.getCourseId());
        Boolean languageIsExist = languageService.isExist(word.getLanguageName());
        if (userIsExist && courseIsExist && languageIsExist) {
            wordService.save(word);
            Optional<WordEntity> savedWord = wordService.findByUserId(word.getUserId());
            if (savedWord.isPresent()) {
                return new ResponseEntity<>(savedWord.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{wordId}")
    public ResponseEntity<WordEntity> getWordById(@PathVariable Long wordId) {
        Boolean isExist = wordService.isExist(wordId);
        if (isExist) {
            return new ResponseEntity<>(wordService.findById(wordId).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<WordEntity> udpateWord(@RequestBody WordEntity word) {
        Boolean userIsExist = userService.isExist(word.getUserId());
        Boolean courseIsExist = courseService.isExist(word.getUserId(), word.getCourseId());
        Boolean languageIsExist = languageService.isExist(word.getLanguageName());
        Boolean wordIsExist = wordService.isExist(word.getWordId());
        if (userIsExist && courseIsExist && languageIsExist) {
            if (wordIsExist) {
                wordService.update(word);
                Optional<WordEntity> updatedWord = wordService.findById(word.getWordId());
                if (updatedWord.isPresent()) {
                    return new ResponseEntity<>(updatedWord.get(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{wordId}")
    public ResponseEntity<?> deleteWord(@PathVariable Long wordId) {
        Boolean isExist = wordService.isExist(wordId);
        if (isExist) {
            wordService.delete(wordId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
