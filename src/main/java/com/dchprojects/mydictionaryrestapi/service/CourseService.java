package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateCourseRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.CourseEntity;

import java.util.List;

public interface CourseService {

    public List<CourseEntity> listByUserId(Long userId);

    public CourseEntity create(CreateCourseRequest createCourseRequest);

    public void deleteByUserIdAndCourseId(Long userId, Long courseId);

}
