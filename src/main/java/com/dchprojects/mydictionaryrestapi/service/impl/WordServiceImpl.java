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
    public Boolean isExist(Long wordId) { return wordRepository.existsByWordId(wordId); }

    @Override
    public Optional<WordEntity> findById(Long wordId) { return wordRepository.findById(wordId); }

    @Override
    public Optional<WordEntity> findByUserId(Long userId) { return wordRepository.findByUserId(userId); }

    @Override
    public void save(WordEntity word) { wordRepository.save(word); }

    @Override
    public void update(WordEntity word) {
        WordEntity wordForUpdate = wordRepository.findById(word.getWordId()).get();
        wordForUpdate.setWordText(word.getWordText());
        wordForUpdate.setWordDescription(word.getWordDescription());
        wordRepository.save(wordForUpdate);
    }

    @Override
    public void deleteByUserIdAndCourseIdAndWordId(Long userId, Long courseId, Long wordId) { wordRepository.deleteByUserIdAndCourseIdAndWordId(userId, courseId, wordId); }

}
