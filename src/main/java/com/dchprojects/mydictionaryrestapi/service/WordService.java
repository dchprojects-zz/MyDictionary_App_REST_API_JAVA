package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.entity.WordEntity;

import java.util.List;
import java.util.Optional;

public interface WordService {

    public List<WordEntity> listAll();

    public Boolean isExist(Long wordId);

    public Boolean existsByUserIdAndCourseIdAndLanguageIdAndWordTextAndWordDescriptionAndLanguageName(Long userId,
                                                                                                      Long courseId,
                                                                                                      Long languageId,
                                                                                                      String wordText,
                                                                                                      String wordDescription,
                                                                                                      String languageName);

    public Optional<WordEntity> findByUserIdAndCourseIdAndLanguageIdAndWordTextAndWordDescriptionAndLanguageName(Long userId,
                                                                                                                 Long courseId,
                                                                                                                 Long languageId,
                                                                                                                 String wordText,
                                                                                                                 String wordDescription,
                                                                                                                 String languageName);

    public Optional<WordEntity> findByUserIdAndCourseIdAndWordId(Long userId, Long courseId, Long wordId);

    public void save(WordEntity word);

    public void update(WordEntity word);

    public void deleteByUserIdAndCourseIdAndWordId(Long userId, Long courseId, Long wordId);

    public void deleteAllByUserId(Long userId);

}