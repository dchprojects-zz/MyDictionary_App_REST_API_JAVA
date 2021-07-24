package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.entity.CourseEntity;
import com.dchprojects.mydictionaryrestapi.repository.CourseRepository;
import com.dchprojects.mydictionaryrestapi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<CourseEntity> listByUserId(Integer id) { return courseRepository.findAllByUser_id(id); }

    @Override
    public Optional<CourseEntity> findByLanguageNameAndUserId(String languageName, Integer userId) { return courseRepository.findByLanguage_nameAndUser_id(languageName, userId); }

    @Override
    public Boolean isExist(String languageName, Integer userId) { return courseRepository.findByLanguage_nameAndUser_id(languageName, userId) != null; }

    @Override
    public Boolean isExist(Integer userId, Integer courseId) { return courseRepository.existsByUser_idAndAndId(userId, courseId); }

    @Override
    public void save(CourseEntity course) { courseRepository.save(course); }

    @Override
    public void deleteByUserIdAndCourseId(Integer userId, Integer courseId) { courseRepository.deleteByUser_idAndAndId(userId, courseId); }

}
