package com.dchprojects.mydictionaryrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dchprojects.mydictionaryrestapi.entity.LanguageEntity;

public interface LanguageRepository extends JpaRepository<LanguageEntity, Integer> {

}
