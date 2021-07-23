package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dchprojects.mydictionaryrestapi.entity.WordEntity;

import java.util.List;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    public List<WordEntity> listAll() {
        return wordRepository.findAll();
    }

    public Boolean isExist(Integer id) { return wordRepository.findById(id).isPresent(); }

    public WordEntity findById(Integer id) { return wordRepository.findById(id).get(); }

    public WordEntity findByUserId(Integer id) { return wordRepository.findByUser_id(id); }

    public void save(WordEntity word) { wordRepository.save(word); }

    public void update(WordEntity word) {
        WordEntity wordForUpdate = wordRepository.findById(word.getId()).get();
        wordForUpdate.setWord(word.getWord());
        wordForUpdate.setWord_description(word.getWord_description());
        wordRepository.save(wordForUpdate);
    }

    public void delete(Integer id) { wordRepository.deleteById(id); }

}
