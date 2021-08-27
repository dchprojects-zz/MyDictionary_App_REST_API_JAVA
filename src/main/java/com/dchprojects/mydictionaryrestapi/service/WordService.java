package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateWordRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.UpdateWordRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.WordResponse;

import java.util.List;

public interface WordService {

    public List<WordResponse> listAll();

    public WordResponse create(CreateWordRequest createWordRequest);

    public WordResponse update(UpdateWordRequest updateWordRequest);

    public void deleteByUserIdAndCourseIdAndWordId(Long userId, Long courseId, Long wordId);

}