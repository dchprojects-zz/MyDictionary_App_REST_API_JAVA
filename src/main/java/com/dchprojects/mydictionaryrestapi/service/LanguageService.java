package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateLanguageRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.LanguageEntity;

import java.util.List;

public interface LanguageService {

    public List<LanguageEntity> listAll();

    public LanguageEntity findById(Long languageId);

    public LanguageEntity create(CreateLanguageRequest createLanguageRequest);

}
