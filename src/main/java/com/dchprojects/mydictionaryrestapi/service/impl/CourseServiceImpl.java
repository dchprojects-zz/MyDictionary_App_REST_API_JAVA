package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateCourseRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.CourseEntity;
import com.dchprojects.mydictionaryrestapi.repository.CourseRepository;
import com.dchprojects.mydictionaryrestapi.service.CourseService;
import com.dchprojects.mydictionaryrestapi.service.LanguageService;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserService userService;
    private final LanguageService languageService;

    @Override
    public List<CourseEntity> listByUserId(Long userId) { return courseRepository.findAllByUserId(userId); }

    @Override
    public Optional<CourseEntity> findByLanguageNameAndUserId(String languageName, Long userId) { return courseRepository.findByLanguageNameAndUserId(languageName, userId); }

    @Override
    public Boolean existsByLanguageNameAndUserId(String languageName, Long userId) {
        return courseRepository.existsByLanguageNameAndUserId(languageName, userId);
    }

    @Override
    public Boolean existsByUserIdAndCourseId(Long userId, Long courseId) {
        return courseRepository.existsByUserIdAndCourseId(userId, courseId);
    }

    @Override
    public CourseEntity create(CreateCourseRequest createCourseRequest) {
        Boolean existsByUserId = userService.existsByUserId(createCourseRequest.getUserId());
        Boolean existsByLanguageNameAndUserId = courseRepository.existsByLanguageNameAndUserId(createCourseRequest.getLanguageName(), createCourseRequest.getUserId());
        Boolean existsByLanguageIdAndLanguageName = languageService.existsByLanguageIdAndLanguageName(createCourseRequest.getLanguageId(), createCourseRequest.getLanguageName());
        if (existsByUserId && existsByLanguageIdAndLanguageName) {
            if (existsByLanguageNameAndUserId) {
                throw new ValidationException("Course exists!");
            } else {
                CourseEntity newCourse = new CourseEntity();

                newCourse.setUserId(createCourseRequest.getUserId());
                newCourse.setLanguageId(createCourseRequest.getLanguageId());
                newCourse.setLanguageName(createCourseRequest.getLanguageName());

                CourseEntity createdCourse = courseRepository.save(newCourse);
                return createdCourse;
            }
        } else {
            throw new NoSuchElementException("Course not found!");
        }
    }

    @Override
    public void deleteByUserIdAndCourseId(Long userId, Long courseId) { courseRepository.deleteByUserIdAndCourseId(userId, courseId); }

}
