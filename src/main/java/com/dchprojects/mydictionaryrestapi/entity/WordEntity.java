package com.dchprojects.mydictionaryrestapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "word")
public class WordEntity {

    @JsonProperty("user_id")
    @Column(name = "user_id")
    private Integer userId;

    @JsonProperty("id")
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty("word")
    @Column(name = "word")
    private String word;

    @JsonProperty("word_description")
    @Column(name = "word_description")
    private String wordDescription;

    @JsonProperty("word_language")
    @Column(name = "word_language")
    private String wordLanguage;

    @JsonProperty("created_at")
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @JsonProperty("updated_at")
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    public WordEntity() {

    }

    public WordEntity(Integer userId,
                      Integer id,
                      String word,
                      String wordDescription,
                      String wordLanguage,
                      Timestamp createdAt,
                      Timestamp updatedAt) {

        this.userId = userId;
        this.id = id;
        this.word = word;
        this.wordDescription = wordDescription;
        this.wordLanguage = wordLanguage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordDescription() {
        return wordDescription;
    }

    public void setWordDescription(String wordDescription) {
        this.wordDescription = wordDescription;
    }

    public String getWordLanguage() {
        return wordLanguage;
    }

    public void setWordLanguage(String wordLanguage) {
        this.wordLanguage = wordLanguage;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}
