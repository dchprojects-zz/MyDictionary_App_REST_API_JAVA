package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.entity.CourseEntity;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    public List<CourseEntity> listByUserId(Long userId);

    public Optional<CourseEntity> findByLanguageNameAndUserId(String languageName, Long userId);

    public Boolean existsByLanguageNameAndUserId(String languageName, Long userId);

    public Boolean existsByUserIdAndCourseId(Long userId, Long courseId);

    public CourseEntity create(CourseEntity course);

    public void deleteByUserIdAndCourseId(Long userId, Long courseId);

}
