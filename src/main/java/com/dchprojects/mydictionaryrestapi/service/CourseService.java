package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.entity.CourseEntity;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    public List<CourseEntity> listByUserId(Long userId);

    public Optional<CourseEntity> findByLanguageNameAndUserId(String languageName, Long userId);

    public Boolean isExist(String languageName, Long userId);

    public Boolean isExist(Long userId, Long courseId);

    public void save(CourseEntity course);

    public void deleteByUserIdAndCourseId(Long userId, Long courseId);

    public void deleteAllByUserId(Long userId);

}
