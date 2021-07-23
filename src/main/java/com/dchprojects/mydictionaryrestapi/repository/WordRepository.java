package com.dchprojects.mydictionaryrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dchprojects.mydictionaryrestapi.entity.WordEntity;

public interface WordRepository extends JpaRepository<WordEntity, Integer> {
    WordEntity findByUser_id(Integer id);
}
