package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateLanguageRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.LanguageResponse;

import java.util.List;

public interface LanguageService {

    public List<LanguageResponse> listAll();

    public LanguageResponse findById(Long languageId);

    public LanguageResponse create(CreateLanguageRequest createLanguageRequest);

}
