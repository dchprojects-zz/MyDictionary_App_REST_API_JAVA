package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.entity.WordEntity;

import java.util.List;
import java.util.Optional;

public interface WordService {

    public List<WordEntity> listAll();

    public Boolean isExist(Long wordId);

    public Optional<WordEntity> findById(Long wordId);

    public Optional<WordEntity> findByUserId(Long userId);

    public void save(WordEntity word);

    public void update(WordEntity word);

    public void deleteByUserIdAndCourseIdAndWordId(Long userId, Long courseId, Long wordId);

    public void deleteAllByUserId(Long userId);

}