package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateLanguageRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.LanguageResponse;
import com.dchprojects.mydictionaryrestapi.domain.entity.LanguageEntity;
import com.dchprojects.mydictionaryrestapi.entity_converter.EntityConverter;
import com.dchprojects.mydictionaryrestapi.repository.LanguageRepository;
import com.dchprojects.mydictionaryrestapi.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    @Override
    public List<LanguageResponse> listAll() {
        return languageRepository.findAll()
                .stream()
                .map(EntityConverter::languageEntityToLanguageResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LanguageResponse findById(Long languageId) {
        Boolean languageExists = languageRepository.existsByLanguageId(languageId);
        if (languageExists) {
            return EntityConverter.languageEntityToLanguageResponse(languageRepository.findById(languageId).get());
        } else {
            throw new NoSuchElementException("Language not found");
        }
    }

    @Override
    public LanguageResponse create(CreateLanguageRequest createLanguageRequest) {
        Boolean existsByLanguageName = languageRepository.existsByLanguageName(createLanguageRequest.getLanguageName());
        if (existsByLanguageName) {
            throw new ValidationException("Language name exists!");
        } else {
            LanguageEntity newLanguage = new LanguageEntity();

            newLanguage.setLanguageName(createLanguageRequest.getLanguageName());

            return EntityConverter.languageEntityToLanguageResponse(languageRepository.save(newLanguage));
        }
    }

}
