package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateWordRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.UpdateWordRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.WordEntity;
import com.dchprojects.mydictionaryrestapi.repository.WordRepository;
import com.dchprojects.mydictionaryrestapi.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;

    @Override
    public List<WordEntity> listAll() {
        return wordRepository.findAll();
    }

    @Override
    public WordEntity create(CreateWordRequest createWordRequest) {
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

            WordEntity createdWord = wordRepository.save(newWord);
            return createdWord;
        }
    }

    @Override
    public WordEntity update(UpdateWordRequest updateWordRequest) {
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

            WordEntity updatedWord = wordRepository.save(wordForUpdate);
            return updatedWord;
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
