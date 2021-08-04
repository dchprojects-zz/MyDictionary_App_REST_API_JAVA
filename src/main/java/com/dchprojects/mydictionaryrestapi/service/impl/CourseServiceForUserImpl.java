package com.dchprojects.mydictionaryrestapi.service.impl;

import com.dchprojects.mydictionaryrestapi.repository.CourseRepository;
import com.dchprojects.mydictionaryrestapi.service.CourseServiceForUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceForUserImpl implements CourseServiceForUser {

    private CourseRepository courseRepository;

    @Override
    public void deleteAllByUserId(Long userId) { courseRepository.deleteAllByUserId(userId); }

}
