package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.entity.CourseEntity;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    public List<CourseEntity> listByUserId(Integer id);

    public Optional<CourseEntity> findByLanguageNameAndUserId(String languageName, Integer userId);

    public Boolean isExist(String languageName, Integer userId);

    public Boolean isExist(Integer userId, Integer courseId);

    public void save(CourseEntity course);

    public void deleteByUserIdAndCourseId(Integer userId, Integer courseId);

}
