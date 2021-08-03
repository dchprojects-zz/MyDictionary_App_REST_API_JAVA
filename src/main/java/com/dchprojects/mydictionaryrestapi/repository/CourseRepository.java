package com.dchprojects.mydictionaryrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dchprojects.mydictionaryrestapi.domain.entity.CourseEntity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    List<CourseEntity> findAllByUserId(Long userId);

    Optional<CourseEntity> findByLanguageNameAndUserId(String languageName, Long userId);

    Boolean existsByLanguageNameAndUserId(String languageName, Long userId);

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
