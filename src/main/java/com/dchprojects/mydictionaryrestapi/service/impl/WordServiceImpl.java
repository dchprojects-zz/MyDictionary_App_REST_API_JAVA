package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateWordRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.UpdateWordRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.WordResponse;
import com.dchprojects.mydictionaryrestapi.domain.entity.WordEntity;
import com.dchprojects.mydictionaryrestapi.entity_converter.EntityConverter;
import com.dchprojects.mydictionaryrestapi.repository.WordRepository;
import com.dchprojects.mydictionaryrestapi.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;

    @Override
    public List<WordResponse> listAllByUserId(Long userId) {
        return wordRepository
                .findAllByUserId(userId)
                .stream()
                .map(EntityConverter::wordEntityToWordResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<WordResponse> listAllByCourseId(Long courseId) {
        return wordRepository
                .findAllByCourseId(courseId)
                .stream()
                .map(EntityConverter::wordEntityToWordResponse)
                .collect(Collectors.toList());
    }

    @Override
    public WordResponse create(CreateWordRequest createWordRequest) {

        Boolean existsByUserIdAndCourseIdAndWordText = wordRepository.existsByUserIdAndCourseIdAndWordText(
                createWordRequest.getUserId(),
                createWordRequest.getCourseId(),
                createWordRequest.getWordText()
        );

        if (existsByUserIdAndCourseIdAndWordText) {
            throw new ValidationException("Word exists!");
        } else {
            WordEntity newWord = new WordEntity();

            newWord.setUserId(createWordRequest.getUserId());
            newWord.setCourseId(createWordRequest.getCourseId());
            newWord.setLanguageId(createWordRequest.getLanguageId());
            newWord.setWordText(createWordRequest.getWordText());
            newWord.setWordDescription(createWordRequest.getWordDescription());
            newWord.setLanguageName(createWordRequest.getLanguageName());

            return EntityConverter.wordEntityToWordResponse(wordRepository.save(newWord));
        }

    }

    @Override
    public WordResponse update(UpdateWordRequest updateWordRequest) {

        Boolean existsByUserAndCourseAndWordId = wordRepository.existsByUserIdAndCourseIdAndWordId(updateWordRequest.getUserId(),
                updateWordRequest.getCourseId(),
                updateWordRequest.getWordId());

        Boolean existsByUserIdAndCourseIdAndWordText = wordRepository.existsByUserIdAndCourseIdAndWordText(
                updateWordRequest.getUserId(),
                updateWordRequest.getCourseId(),
                updateWordRequest.getWordText()
        );

        if (existsByUserAndCourseAndWordId) {

            if (existsByUserIdAndCourseIdAndWordText) {
                throw new ValidationException("Word exists!");
            } else {
                WordEntity wordForUpdate = wordRepository.findByUserIdAndCourseIdAndWordId(updateWordRequest.getUserId(),
                        updateWordRequest.getCourseId(),
                        updateWordRequest.getWordId()).get();

                wordForUpdate.setWordText(updateWordRequest.getWordText());
                wordForUpdate.setWordDescription(updateWordRequest.getWordDescription());

                return EntityConverter.wordEntityToWordResponse(wordRepository.save(wordForUpdate));
            }

        } else {
            throw new NoSuchElementException("Word not found!");
        }

    }

    @Override
    public void deleteByUserIdAndCourseIdAndWordId(Long userId, Long courseId, Long wordId) {
        Boolean existsByUserAndCourseAndWordId = wordRepository.existsByUserIdAndCourseIdAndWordId(userId, courseId, wordId);
        if (existsByUserAndCourseAndWordId) {
            wordRepository.deleteByUserIdAndCourseIdAndWordId(userId, courseId, wordId);
        } else {
            throw new NoSuchElementException("Word not found!");
        }
    }

}
