package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateWordRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.UpdateWordRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.WordEntity;

import java.util.List;

public interface WordService {

    public List<WordEntity> listAll();

    public WordEntity create(CreateWordRequest createWordRequest);

    public WordEntity update(UpdateWordRequest updateWordRequest);

    public void deleteByUserIdAndCourseIdAndWordId(Long userId, Long courseId, Long wordId);

}