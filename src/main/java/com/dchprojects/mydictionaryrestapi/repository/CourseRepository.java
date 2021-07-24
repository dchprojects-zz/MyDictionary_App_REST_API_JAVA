package com.dchprojects.mydictionaryrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dchprojects.mydictionaryrestapi.entity.CourseEntity;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
    List<CourseEntity> findAllByUserId(Integer user_id);
    Optional<CourseEntity> findByLanguageNameAndUserId(String languageName, Integer id);
    Boolean existsByUserIdAndId(Integer userId, Integer id);
    Void deleteByUserIdAndId(Integer userId, Integer id);
}
