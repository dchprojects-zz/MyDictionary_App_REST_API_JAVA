package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.entity.CourseEntity;
import com.dchprojects.mydictionaryrestapi.repository.CourseRepository;
import com.dchprojects.mydictionaryrestapi.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public List<CourseEntity> listByUserId(Long userId) { return courseRepository.findAllByUserId(userId); }

    @Override
    public Optional<CourseEntity> findByLanguageNameAndUserId(String languageName, Long userId) { return courseRepository.findByLanguageNameAndUserId(languageName, userId); }

    @Override
    public Boolean isExist(String languageName, Long userId) { return courseRepository.existsByLanguageNameAndUserId(languageName, userId); }

    @Override
    public Boolean isExist(Long userId, Long courseId) { return courseRepository.existsByUserIdAndCourseId(userId, courseId); }

    @Override
    public void save(CourseEntity course) { courseRepository.save(course); }

    @Override
    public void deleteByUserIdAndCourseId(Long userId, Long courseId) { courseRepository.deleteByUserIdAndCourseId(userId, courseId); }

    @Override
    public void deleteAllByUserId(Long userId) { courseRepository.deleteAllByUserId(userId); }

}
