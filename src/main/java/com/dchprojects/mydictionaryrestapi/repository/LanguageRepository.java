package com.dchprojects.mydictionaryrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dchprojects.mydictionaryrestapi.domain.entity.LanguageEntity;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity, Long> {

    Optional<LanguageEntity> findByLanguageName(String languageName);

    Boolean existsByLanguageId(Long languageId);

    Boolean existsByLanguageName(String languageName);

    Boolean existsByLanguageIdAndLanguageName(Long languageId, String languageName);

}
