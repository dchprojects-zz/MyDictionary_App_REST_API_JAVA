package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.entity.LanguageEntity;

import java.util.List;
import java.util.Optional;

public interface LanguageService {

    public List<LanguageEntity> listAll();

    public Boolean isExist(Long languageId);

    public Boolean isExist(String languageName);

    public Boolean isExist(Long languageId, String languageName);

    public LanguageEntity findById(Long languageId);

    public Optional<LanguageEntity> findByLanguageName(String languageName);

    public void save(LanguageEntity language);

}
