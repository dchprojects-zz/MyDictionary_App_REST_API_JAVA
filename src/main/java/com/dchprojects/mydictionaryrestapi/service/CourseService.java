package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateCourseRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.CourseEntity;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    public List<CourseEntity> listByUserId(Long userId);

    public Optional<CourseEntity> findByLanguageNameAndUserId(String languageName, Long userId);

    public CourseEntity create(CreateCourseRequest createCourseRequest);

    public void deleteByUserIdAndCourseId(Long userId, Long courseId);

}
