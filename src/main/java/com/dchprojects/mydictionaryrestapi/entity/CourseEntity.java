package com.dchprojects.mydictionaryrestapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "course")
public class CourseEntity {

    @JsonProperty("user_id")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @JsonProperty("course_id")
    @Column(name = "course_id", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @JsonProperty("language_id")
    @Column(name = "language_id", nullable = false)
    private Long languageId;

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

    public CourseEntity() {

    }

    public CourseEntity(Long userId,
                        Long courseId,
                        Long languageId,
                        String languageName,
                        Timestamp createdAt,
                        Timestamp updatedAt) {

        this.userId = userId;
        this.courseId = courseId;
        this.languageId = languageId;
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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getLanguageId() {  return languageId; }

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

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}
