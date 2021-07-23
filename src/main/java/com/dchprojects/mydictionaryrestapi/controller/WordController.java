package com.dchprojects.mydictionaryrestapi.controller;

import com.dchprojects.mydictionaryrestapi.entity.WordEntity;
import com.dchprojects.mydictionaryrestapi.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
        WordEntity savedWord = wordService.findByUserId(word.getUser_id());
        return new ResponseEntity<>(savedWord, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WordEntity> getWordById(@PathVariable Integer id) {
        try {
            WordEntity word = wordService.findById(id);
            return new ResponseEntity<>(word, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<WordEntity> udpateWord(@RequestBody WordEntity word,
                                                 @PathVariable Integer id) {
        Boolean isExist = wordService.isExist(id);
        if (isExist) {
            wordService.update(word);
            WordEntity updatedWord = wordService.findById(id);
            return new ResponseEntity<>(updatedWord, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWord(@PathVariable Integer id) {
        Boolean isExist = wordService.isExist(id);
        if (isExist) {
            wordService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
