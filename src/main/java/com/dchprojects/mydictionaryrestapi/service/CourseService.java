package com.dchprojects.mydictionaryrestapi.service;

import com.dchprojects.mydictionaryrestapi.domain.dto.CourseResponse;
import com.dchprojects.mydictionaryrestapi.domain.dto.CreateCourseRequest;

import java.util.List;

public interface CourseService {

    public List<CourseResponse> listByUserId(Long userId);

    public CourseResponse create(CreateCourseRequest createCourseRequest);

    public void deleteByUserIdAndCourseId(Long userId, Long courseId);

}
