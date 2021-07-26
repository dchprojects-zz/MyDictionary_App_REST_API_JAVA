package com.dchprojects.mydictionaryrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dchprojects.mydictionaryrestapi.entity.WordEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<WordEntity, Long> {
    Optional<WordEntity> findByUserId(Long userId);
    Boolean existsByWordId(Long wordId);
    void deleteByUserIdAndCourseIdAndWordId(Long userId, Long courseId, Long wordId);
}
