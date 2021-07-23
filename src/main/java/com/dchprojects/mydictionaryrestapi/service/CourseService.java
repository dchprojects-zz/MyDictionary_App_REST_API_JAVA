package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.entity.CourseEntity;
import com.dchprojects.mydictionaryrestapi.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseEntity> listByUserId(Integer id) { return courseRepository.findAllByUser_id(id); }

    public CourseEntity findByLanguageNameAndUserId(String languageName, Integer userId) { return courseRepository.findByLanguage_nameAndUser_id(languageName, userId); }

    public Boolean isExist(String languageName, Integer userId) { return courseRepository.findByLanguage_nameAndUser_id(languageName, userId) != null; }

    public Boolean isExist(Integer userId, Integer courseId) { return courseRepository.existsByUser_idAndAndId(userId, courseId); }

    public void save(CourseEntity course) { courseRepository.save(course); }

    public void deleteByUserIdAndCourseId(Integer userId, Integer courseId) { courseRepository.deleteByUser_idAndAndId(userId, courseId); }

}
