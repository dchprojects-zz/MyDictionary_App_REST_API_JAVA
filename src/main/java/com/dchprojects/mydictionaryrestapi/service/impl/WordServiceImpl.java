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
    public WordResponse create(CreateWordRequest createWordRequest) {
        Boolean wordIsExist = wordRepository.existsByUserIdAndCourseIdAndLanguageIdAndLanguageNameAndWordText(
                createWordRequest.getUserId(),
                createWordRequest.getCourseId(),
                createWordRequest.getLanguageId(),
                createWordRequest.getLanguageName(),
                createWordRequest.getWordText()
        );
        if (wordIsExist) {
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
        Boolean wordIsExist = wordRepository.existsByUserIdAndCourseIdAndLanguageIdAndLanguageNameAndWordText(
                updateWordRequest.getUserId(),
                updateWordRequest.getCourseId(),
                updateWordRequest.getLanguageId(),
                updateWordRequest.getLanguageName(),
                updateWordRequest.getWordText()
        );

        if (wordIsExist) {
            throw new ValidationException("Word exists!");
        } else {
            WordEntity wordForUpdate = wordRepository.findById(updateWordRequest.getWordId()).get();

            wordForUpdate.setWordText(updateWordRequest.getWordText());
            wordForUpdate.setWordDescription(updateWordRequest.getWordDescription());

            return EntityConverter.wordEntityToWordResponse(wordRepository.save(wordForUpdate));
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
