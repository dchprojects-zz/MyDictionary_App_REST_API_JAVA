package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.domain.dto.CourseResponse;
import com.dchprojects.mydictionaryrestapi.domain.dto.CreateCourseRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.CourseEntity;
import com.dchprojects.mydictionaryrestapi.entity_converter.EntityConverter;
import com.dchprojects.mydictionaryrestapi.repository.CourseRepository;
import com.dchprojects.mydictionaryrestapi.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public List<CourseResponse> listByUserId(Long userId) {
        return courseRepository.findAllByUserId(userId)
                .stream()
                .map(EntityConverter::courseEntityToCourseResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponse create(CreateCourseRequest createCourseRequest) {
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

            return EntityConverter.courseEntityToCourseResponse(courseRepository.save(newCourse));
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
