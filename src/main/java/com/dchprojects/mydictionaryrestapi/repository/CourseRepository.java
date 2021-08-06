package com.dchprojects.mydictionaryrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dchprojects.mydictionaryrestapi.domain.entity.CourseEntity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    List<CourseEntity> findAllByUserId(Long userId);

    Boolean existsByUserIdAndLanguageNameAndLanguageId(Long userId, String languageName, Long languageId);

    Boolean existsByUserIdAndCourseId(Long userId, Long courseId);

    @Modifying
    @Transactional
    @Query(value="delete from CourseEntity course where course.userId = ?1 and course.courseId = ?1")
    void deleteByUserIdAndCourseId(Long userId, Long courseId);

    @Modifying
    @Transactional
    @Query(value="delete from CourseEntity course where course.userId = ?1")
    void deleteAllByUserId(Long userId);
}
