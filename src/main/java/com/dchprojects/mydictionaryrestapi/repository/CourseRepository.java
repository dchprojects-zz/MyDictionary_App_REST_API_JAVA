package com.dchprojects.mydictionaryrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dchprojects.mydictionaryrestapi.domain.entity.CourseEntity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    List<CourseEntity> findAllByUserId(Long userId);

    Boolean existsByUserIdAndLanguageNameAndLanguageId(Long userId, String languageName, Long languageId);

    Boolean existsByUserIdAndCourseId(Long userId, Long courseId);

    @Transactional
    @Modifying
    @Query(value = "delete from CourseEntity course where course.userId=:#{#userId} and course.courseId=:#{#courseId}")
    void deleteByUserIdAndCourseId(@Param("userId") Long userId, @Param("courseId") Long courseId);

    @Modifying
    @Transactional
    @Query(value = "delete from CourseEntity course where course.userId=:#{#userId}")
    void deleteAllByUserId(@Param("userId") Long userId);

}
