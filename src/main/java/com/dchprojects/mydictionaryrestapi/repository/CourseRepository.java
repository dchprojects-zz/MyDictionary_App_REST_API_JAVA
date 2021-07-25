package com.dchprojects.mydictionaryrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dchprojects.mydictionaryrestapi.entity.CourseEntity;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    List<CourseEntity> findAllByUserId(Long userId);
    Optional<CourseEntity> findByLanguageNameAndUserId(String languageName, Long userId);
    Boolean existsByUserIdAndCourseId(Long userId, Long courseId);
    Void deleteByUserIdAndCourseId(Long userId, Long courseId);
}
