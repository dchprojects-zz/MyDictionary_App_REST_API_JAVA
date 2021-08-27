package com.dchprojects.mydictionaryrestapi.domain.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "words")
public class WordEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "word_id", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wordId;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "language_id", nullable = false)
    private Long languageId;

    @Column(name = "word_text", nullable = false)
    private String wordText;

    @Column(name = "word_description", nullable = false)
    private String wordDescription;

    @Column(name = "language_name", nullable = false)
    private String languageName;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

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

        this.setUserId(userId);
        this.setWordId(wordId);
        this.setCourseId(courseId);
        this.setLanguageId(languageId);
        this.setWordText(wordText);
        this.setWordDescription(wordDescription);
        this.setLanguageName(languageName);
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);

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
