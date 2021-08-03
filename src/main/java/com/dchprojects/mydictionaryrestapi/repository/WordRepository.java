package com.dchprojects.mydictionaryrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dchprojects.mydictionaryrestapi.domain.entity.WordEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<WordEntity, Long> {

    Optional<WordEntity> findByUserIdAndCourseIdAndLanguageIdAndWordTextAndWordDescriptionAndLanguageName(Long userId,
                                                                                                          Long courseId,
                                                                                                          Long languageId,
                                                                                                          String wordText,
                                                                                                          String wordDescription,
                                                                                                          String languageName);

    Optional<WordEntity> findByUserIdAndCourseIdAndWordId(Long userId, Long courseId, Long wordId);

    Boolean existsByWordId(Long wordId);

    Boolean existsByUserIdAndCourseIdAndLanguageIdAndWordTextAndWordDescriptionAndLanguageName(Long userId,
                                                                                               Long courseId,
                                                                                               Long languageId,
                                                                                               String wordText,
                                                                                               String wordDescription,
                                                                                               String languageName);

    @Modifying
    @Transactional
    @Query(value="delete from WordEntity word where word.userId = ?1 and word.courseId = ?1 and word.wordId = ?1")
    void deleteByUserIdAndCourseIdAndWordId(Long userId, Long courseId, Long wordId);

    @Modifying
    @Transactional
    @Query(value="delete from WordEntity word where word.userId = ?1")
    void deleteAllByUserId(Long userId);

}
