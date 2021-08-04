package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.entity.LanguageEntity;

import java.util.List;
import java.util.Optional;

public interface LanguageService {

    public List<LanguageEntity> listAll();

    public Boolean existsByLanguageId(Long languageId);

    public Boolean existsByLanguageName(String languageName);

    public Boolean existByLanguageIdAndLanguageName(Long languageId, String languageName);

    public LanguageEntity findById(Long languageId);

    public Optional<LanguageEntity> findByLanguageName(String languageName);

    public LanguageEntity create(LanguageEntity language);

}
