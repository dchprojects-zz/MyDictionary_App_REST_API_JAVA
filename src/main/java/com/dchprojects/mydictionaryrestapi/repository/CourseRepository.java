package com.dchprojects.mydictionaryrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dchprojects.mydictionaryrestapi.entity.CourseEntity;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
    List<CourseEntity> findAllByUser_id(Integer id);
    CourseEntity findByLanguage_nameAndUser_id(String languageName, Integer id);
    Boolean existsByUser_idAndAndId(Integer userId, Integer id);
    Void deleteByUser_idAndAndId(Integer userId, Integer id);
}
