package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.entity.WordEntity;
import com.dchprojects.mydictionaryrestapi.repository.WordRepository;
import com.dchprojects.mydictionaryrestapi.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    private WordRepository wordRepository;

    @Override
    public List<WordEntity> listAll() {
        return wordRepository.findAll();
    }

    @Override
    public Boolean isExist(Integer id) { return wordRepository.findById(id).isPresent(); }

    @Override
    public Optional<WordEntity> findById(Integer id) { return wordRepository.findById(id); }

    @Override
    public Optional<WordEntity> findByUserId(Integer id) { return wordRepository.findByUser_id(id); }

    @Override
    public void save(WordEntity word) { wordRepository.save(word); }

    @Override
    public void update(WordEntity word) {
        WordEntity wordForUpdate = wordRepository.findById(word.getId()).get();
        wordForUpdate.setWord(word.getWord());
        wordForUpdate.setWord_description(word.getWord_description());
        wordRepository.save(wordForUpdate);
    }

    @Override
    public void delete(Integer id) { wordRepository.deleteById(id); }

}
