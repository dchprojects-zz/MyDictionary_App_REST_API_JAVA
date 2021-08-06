package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateLanguageRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.LanguageEntity;
import com.dchprojects.mydictionaryrestapi.repository.LanguageRepository;
import com.dchprojects.mydictionaryrestapi.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    @Override
    public List<LanguageEntity> listAll() { return languageRepository.findAll(); }

    @Override
    public LanguageEntity findById(Long languageId) {
        Boolean languageExists = languageRepository.existsByLanguageId(languageId);
        if (languageExists) {
            return languageRepository.findById(languageId).get();
        } else {
            throw new NoSuchElementException("Language not found");
        }
    }

    @Override
    public LanguageEntity create(CreateLanguageRequest createLanguageRequest) {
        Boolean existsByLanguageName = languageRepository.existsByLanguageName(createLanguageRequest.getLanguageName());
        if (existsByLanguageName) {
            throw new ValidationException("Language name exists!");
        } else {
            LanguageEntity newLanguage = new LanguageEntity();

            newLanguage.setLanguageName(createLanguageRequest.getLanguageName());

            LanguageEntity createdLanguage = languageRepository.save(newLanguage);
            return createdLanguage;
        }
    }

}
