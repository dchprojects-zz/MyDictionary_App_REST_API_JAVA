package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.entity.LanguageEntity;
import com.dchprojects.mydictionaryrestapi.repository.LanguageRepository;
import com.dchprojects.mydictionaryrestapi.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public List<LanguageEntity> listAll() { return languageRepository.findAll(); }

    @Override
    public Boolean isExist(Long languageId) { return languageRepository.existsByLanguageId(languageId); }

    @Override
    public Boolean isExist(String languageName) { return languageRepository.existsByLanguageName(languageName); }

    @Override
    public Boolean isExist(Long languageId, String languageName) { return languageRepository.existsByLanguageIdAndLanguageName(languageId, languageName); }

    @Override
    public LanguageEntity findById(Long languageId) { return languageRepository.findById(languageId).get(); }

    @Override
    public Optional<LanguageEntity> findByLanguageName(String languageName) { return languageRepository.findByLanguageName(languageName); }

    @Override
    public void save(LanguageEntity language) { languageRepository.save(language); }

}
