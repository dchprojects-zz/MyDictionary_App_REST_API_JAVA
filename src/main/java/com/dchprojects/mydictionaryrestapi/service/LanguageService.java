package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.entity.LanguageEntity;

import java.util.List;
import java.util.Optional;

public interface LanguageService {

    public List<LanguageEntity> listAll();

    public void save(LanguageEntity language);

    public LanguageEntity findById(Long languageId);

    public Optional<LanguageEntity> findByLanguageName(String languageName);

}
