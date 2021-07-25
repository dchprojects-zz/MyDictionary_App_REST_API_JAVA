package com.dchprojects.mydictionaryrestapi.controller;

import com.dchprojects.mydictionaryrestapi.entity.WordEntity;
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

    @GetMapping
    public List<WordEntity> list() {
        return wordService.listAll();
    }

    @PostMapping
    public ResponseEntity<WordEntity> createWord(@RequestBody WordEntity word) {
        wordService.save(word);
        Optional<WordEntity> savedWord = wordService.findByUserId(word.getUserId());
        if (savedWord.isPresent()) {
            return new ResponseEntity<>(savedWord.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{wordId}")
    public ResponseEntity<WordEntity> getWordById(@PathVariable Long wordId) {
        Optional<WordEntity> word = wordService.findById(wordId);
        if (word.isPresent()) {
            return new ResponseEntity<>(word.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<WordEntity> udpateWord(@RequestBody WordEntity word) {
        Boolean isExist = wordService.isExist(word.getWordId());
        if (isExist) {
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
