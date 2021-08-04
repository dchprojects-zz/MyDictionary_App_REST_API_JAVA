package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateWordRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.UpdateWordRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.WordEntity;
import com.dchprojects.mydictionaryrestapi.repository.WordRepository;
import com.dchprojects.mydictionaryrestapi.service.CourseService;
import com.dchprojects.mydictionaryrestapi.service.LanguageService;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import com.dchprojects.mydictionaryrestapi.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final CourseService courseService;
    private final LanguageService languageService;

    @Override
    public List<WordEntity> listAll() {
        return wordRepository.findAll();
    }

    @Override
    public Boolean existsByWordId(Long wordId) {
        return wordRepository.existsByWordId(wordId);
    }

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
    public WordEntity create(CreateWordRequest createWordRequest) {
        Boolean existsByUserIdAndCourseId = courseService.existsByUserIdAndCourseId(createWordRequest.getUserId(), createWordRequest.getCourseId());
        Boolean existsByLanguageIdAndLanguageName = languageService.existsByLanguageIdAndLanguageName(createWordRequest.getLanguageId(), createWordRequest.getLanguageName());
        Boolean wordIsExist = wordRepository.existsByUserIdAndCourseIdAndLanguageIdAndWordTextAndWordDescriptionAndLanguageName(
                createWordRequest.getUserId(),
                createWordRequest.getCourseId(),
                createWordRequest.getLanguageId(),
                createWordRequest.getWordText(),
                createWordRequest.getWordDescription(),
                createWordRequest.getLanguageName()
        );
        if (existsByUserIdAndCourseId && existsByLanguageIdAndLanguageName) {
            if (wordIsExist) {
                throw new ValidationException("Word exists!");
            } else {
                WordEntity createdWord = new WordEntity();
                wordRepository.save(createdWord);
                return createdWord;
            }
        } else {
            throw new NoSuchElementException("Course not found!");
        }
    }

    @Override
    public WordEntity update(UpdateWordRequest updateWordRequest) {
        Boolean existsByUserIdAndCourseId = courseService.existsByUserIdAndCourseId(updateWordRequest.getUserId(), updateWordRequest.getCourseId());
        Boolean existsByLanguageIdAndLanguageName = languageService.existsByLanguageIdAndLanguageName(updateWordRequest.getLanguageId(), updateWordRequest.getLanguageName());
        Boolean wordIsExist = wordRepository.existsByUserIdAndCourseIdAndLanguageIdAndWordTextAndWordDescriptionAndLanguageName(
                updateWordRequest.getUserId(),
                updateWordRequest.getCourseId(),
                updateWordRequest.getLanguageId(),
                updateWordRequest.getWordText(),
                updateWordRequest.getWordDescription(),
                updateWordRequest.getLanguageName()
        );
        if (existsByUserIdAndCourseId && existsByLanguageIdAndLanguageName) {
            if (wordIsExist) {
                throw new ValidationException("Word exists!");
            } else {
                WordEntity wordForUpdate = wordRepository.findById(updateWordRequest.getWordId()).get();
                wordForUpdate.setWordText(updateWordRequest.getWordText());
                wordForUpdate.setWordDescription(updateWordRequest.getWordDescription());
                wordForUpdate = wordRepository.save(wordForUpdate);
                return wordForUpdate;
            }
        } else {
            throw new NoSuchElementException("Course not found!");
        }
    }

    @Override
    public void deleteByUserIdAndCourseIdAndWordId(Long userId, Long courseId, Long wordId) {
        Boolean existsByWordId = wordRepository.existsByWordId(wordId);
        if (existsByWordId) {
            wordRepository.deleteByUserIdAndCourseIdAndWordId(userId, courseId, wordId);
        } else {
            throw new NoSuchElementException("Word not found!");
        }
    }

}
