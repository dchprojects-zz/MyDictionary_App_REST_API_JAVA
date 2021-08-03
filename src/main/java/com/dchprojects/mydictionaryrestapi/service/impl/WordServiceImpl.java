package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.entity.WordEntity;
import com.dchprojects.mydictionaryrestapi.repository.WordRepository;
import com.dchprojects.mydictionaryrestapi.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;

    @Override
    public List<WordEntity> listAll() {
        return wordRepository.findAll();
    }

    @Override
    public Boolean isExist(Long wordId) { return wordRepository.existsByWordId(wordId); }

    @Override
    public Boolean existsByUserIdAndCourseIdAndLanguageIdAndWordTextAndWordDescriptionAndLanguageName(Long userId,
                                                                                                      Long courseId,
                                                                                                      Long languageId,
                                                                                                      String wordText,
                                                                                                      String wordDescription,
                                                                                                      String languageName) {
        return wordRepository.existsByUserIdAndCourseIdAndLanguageIdAndWordTextAndWordDescriptionAndLanguageName(
                userId,
                courseId,
                languageId,
                wordText,
                wordDescription,
                languageName);
    }

    @Override
    public Optional<WordEntity> findByUserIdAndCourseIdAndLanguageIdAndWordTextAndWordDescriptionAndLanguageName(Long userId,
                                                                                                                 Long courseId,
                                                                                                                 Long languageId,
                                                                                                                 String wordText,
                                                                                                                 String wordDescription,
                                                                                                                 String languageName) {
        return wordRepository.findByUserIdAndCourseIdAndLanguageIdAndWordTextAndWordDescriptionAndLanguageName(
                userId,
                courseId,
                languageId,
                wordText,
                wordDescription,
                languageName);
    }

    @Override
    public Optional<WordEntity> findByUserIdAndCourseIdAndWordId(Long userId, Long courseId, Long wordId) {
        return wordRepository.findByUserIdAndCourseIdAndWordId(userId, courseId, wordId);
    }

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

    @Override
    public void deleteAllByUserId(Long userId) { wordRepository.deleteAllByUserId(userId); }

}
