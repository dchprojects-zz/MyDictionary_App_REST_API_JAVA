package com.dchprojects.mydictionaryrestapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "words")
public class WordEntity {

    @JsonProperty("user_id")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @JsonProperty("word_id")
    @Column(name = "word_id", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wordId;

    @JsonProperty("course_id")
    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @JsonProperty("language_id")
    @Column(name = "language_id", nullable = false)
    private Long languageId;

    @JsonProperty("word_text")
    @Column(name = "word_text", nullable = false)
    private String wordText;

    @JsonProperty("word_description")
    @Column(name = "word_description", nullable = false)
    private String wordDescription;

    @JsonProperty("language_name")
    @Column(name = "language_name", nullable = false)
    private String languageName;

    @JsonProperty("created_at")
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @JsonProperty("updated_at")
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    public WordEntity() {

    }

    public WordEntity(Long userId,
                      Long wordId,
                      Long courseId,
                      Long languageId,
                      String wordText,
                      String wordDescription,
                      String languageName,
                      Timestamp createdAt,
                      Timestamp updatedAt) {

        this.userId = userId;
        this.wordId = wordId;
        this.courseId = courseId;
        this.languageId = languageId;
        this.wordText = wordText;
        this.wordDescription = wordDescription;
        this.languageName = languageName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWordId() { return wordId; }

    public void setWordId(Long wordId) {  this.wordId = wordId; }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getLanguageId() { return languageId; }

    public void setLanguageId(Long languageId) {  this.languageId = languageId; }

    public String getWordText() {
        return wordText;
    }

    public void setWordText(String wordText) {
        this.wordText = wordText;
    }

    public String getWordDescription() {
        return wordDescription;
    }

    public void setWordDescription(String wordDescription) {
        this.wordDescription = wordDescription;
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

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}
