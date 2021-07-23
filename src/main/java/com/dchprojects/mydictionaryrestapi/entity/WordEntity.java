package com.dchprojects.mydictionaryrestapi.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity(name = "word")
public class WordEntity {

    private Integer user_id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String word;
    private String word_description;
    private String word_language;
    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp updated_at;

    public WordEntity() {

    }

    public WordEntity(Integer user_id,
                      Integer id,
                      String word,
                      String word_description,
                      String word_language,
                      Timestamp created_at,
                      Timestamp updated_at) {

        this.user_id = user_id;
        this.id = id;
        this.word = word;
        this.word_description = word_description;
        this.word_language = word_language;
        this.created_at = created_at;
        this.updated_at = updated_at;

    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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

    public String getWord_description() {
        return word_description;
    }

    public void setWord_description(String word_description) {
        this.word_description = word_description;
    }

    public String getWord_language() {
        return word_language;
    }

    public void setWord_language(String word_language) {
        this.word_language = word_language;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

}
