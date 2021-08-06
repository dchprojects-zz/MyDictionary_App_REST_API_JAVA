package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateCourseRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.CourseEntity;
import com.dchprojects.mydictionaryrestapi.repository.CourseRepository;
import com.dchprojects.mydictionaryrestapi.service.CourseService;
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

    @Override
    public List<CourseEntity> listByUserId(Long userId) { return courseRepository.findAllByUserId(userId); }

    @Override
    public Optional<CourseEntity> findByLanguageNameAndUserId(String languageName, Long userId) {
        return courseRepository.findByLanguageNameAndUserId(languageName, userId);
    }

    @Override
    public CourseEntity create(CreateCourseRequest createCourseRequest) {
        Boolean courseExists = courseRepository
                .existsByUserIdAndLanguageNameAndLanguageId(createCourseRequest.getUserId(),
                                                            createCourseRequest.getLanguageName(),
                                                            createCourseRequest.getLanguageId());

        if (courseExists) {
            throw new ValidationException("Course exists!");
        } else {
            CourseEntity newCourse = new CourseEntity();

            newCourse.setUserId(createCourseRequest.getUserId());
            newCourse.setLanguageId(createCourseRequest.getLanguageId());
            newCourse.setLanguageName(createCourseRequest.getLanguageName());

            CourseEntity createdCourse = courseRepository.save(newCourse);
            return createdCourse;
        }
    }

    @Override
    public void deleteByUserIdAndCourseId(Long userId, Long courseId) {
        Boolean courseExists = courseRepository.existsByUserIdAndCourseId(userId, courseId);
        if (courseExists) {
            courseRepository.deleteByUserIdAndCourseId(userId, courseId);
        } else {
            throw new NoSuchElementException("Course not found!");
        }
    }

}
