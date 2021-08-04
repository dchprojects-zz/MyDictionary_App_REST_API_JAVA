package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.entity.LanguageEntity;
import com.dchprojects.mydictionaryrestapi.repository.LanguageRepository;
import com.dchprojects.mydictionaryrestapi.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    @Override
    public List<LanguageEntity> listAll() { return languageRepository.findAll(); }

    @Override
    public Boolean existsByLanguageId(Long languageId) {
        return languageRepository.existsByLanguageId(languageId);
    }

    @Override
    public Boolean existsByLanguageName(String languageName) {
        return languageRepository.existsByLanguageName(languageName);
    }

    @Override
    public Boolean existsByLanguageIdAndLanguageName(Long languageId, String languageName) {
        return languageRepository.existsByLanguageIdAndLanguageName(languageId, languageName);
    }

    @Override
    public LanguageEntity findById(Long languageId) { return languageRepository.findById(languageId).get(); }

    @Override
    public Optional<LanguageEntity> findByLanguageName(String languageName) { return languageRepository.findByLanguageName(languageName); }

    @Override
    public LanguageEntity create(LanguageEntity language) {
        Boolean existsByLanguageName = languageRepository.existsByLanguageName(language.getLanguageName());
        if (existsByLanguageName) {
            throw new ValidationException("Language name exists!");
        } else {
            LanguageEntity createdLanguage = languageRepository.save(language);
            return createdLanguage;
        }
    }

}
