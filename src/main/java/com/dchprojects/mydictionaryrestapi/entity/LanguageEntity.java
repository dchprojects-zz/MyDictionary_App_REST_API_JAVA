package com.dchprojects.mydictionaryrestapi.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity(name = "language")
public class LanguageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String language;
    @CreationTimestamp
    private Timestamp created_at;

    public LanguageEntity() {

    }

    public LanguageEntity(Integer id,
                          String language,
                          Timestamp created_at) {
        this.id = id;
        this.language = language;
        this.created_at = created_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

}
