package com.dchprojects.mydictionaryrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dchprojects.mydictionaryrestapi.entity.CourseEntity;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
    List<CourseEntity> findAllByUser_id(Integer id);
    Optional<CourseEntity> findByLanguage_nameAndUser_id(String languageName, Integer id);
    Boolean existsByUser_idAndAndId(Integer userId, Integer id);
    Void deleteByUser_idAndAndId(Integer userId, Integer id);
}
