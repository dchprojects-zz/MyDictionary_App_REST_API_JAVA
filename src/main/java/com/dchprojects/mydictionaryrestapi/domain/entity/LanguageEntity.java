package com.dchprojects.mydictionaryrestapi.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "languages")
public class LanguageEntity {

    @JsonProperty("language_id")
    @Column(name = "language_id", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long languageId;

    @JsonProperty("language_name")
    @Column(name = "language_name", nullable = false)
    private String languageName;

    @JsonProperty("created_at")
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    public LanguageEntity() {

    }

    public LanguageEntity(Long languageId,
                          String languageName,
                          Timestamp createdAt) {

        this.languageId = languageId;
        this.languageName = languageName;
        this.createdAt = createdAt;

    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
