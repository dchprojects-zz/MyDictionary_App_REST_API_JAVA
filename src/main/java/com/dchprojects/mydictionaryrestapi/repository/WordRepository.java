package com.dchprojects.mydictionaryrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dchprojects.mydictionaryrestapi.domain.entity.WordEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<WordEntity, Long> {

    Boolean existsByUserIdAndCourseIdAndWordId(Long userId,
                                               Long courseId,
                                               Long wordId);

    Boolean existsByUserIdAndCourseIdAndWordText(Long userId,
                                                 Long courseId,
                                                 String wordText);

    Optional<WordEntity> findByUserIdAndCourseIdAndWordId(Long userId,
                                                          Long courseId,
                                                          Long wordId);

    Optional<WordEntity> findByUserIdAndCourseIdAndWordText(Long userId,
                                                            Long courseId,
                                                            String wordText);

    List<WordEntity> findAllByUserId(Long userId);

    List<WordEntity> findAllByCourseId(Long courseId);



    @Transactional
    @Modifying
    @Query(value = "delete from WordEntity word where word.userId=:#{#userId} and word.courseId=:#{#courseId} and word.wordId=:#{#wordId}")
    void deleteByUserIdAndCourseIdAndWordId(@Param("userId") Long userId,
                                            @Param("courseId") Long courseId,
                                            @Param("wordId") Long wordId);

    @Transactional
    @Modifying
    @Query(value = "delete from WordEntity word where word.userId=:#{#userId}")
    void deleteAllByUserId(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query(value = "delete from WordEntity word where word.courseId=:#{#courseId}")
    void deleteAllByCourseId(@Param("courseId") Long courseId);

}
