package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateWordRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.UpdateWordRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.WordEntity;

import java.util.List;
import java.util.Optional;

public interface WordService {

    public List<WordEntity> listAll();

    public Boolean existsByWordId(Long wordId);

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

    public WordEntity create(CreateWordRequest createWordRequest);

    public WordEntity update(UpdateWordRequest updateWordRequest);

    public void deleteByUserIdAndCourseIdAndWordId(Long userId, Long courseId, Long wordId);

}