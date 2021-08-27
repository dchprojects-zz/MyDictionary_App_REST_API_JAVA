package com.dchprojects.mydictionaryrestapi.domain.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "courses")
public class CourseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "course_id", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(name = "language_id", nullable = false)
    private Long languageId;

    @Column(name = "language_name", nullable = false)
    private String languageName;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

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

        this.setUserId(userId);
        this.setCourseId(courseId);
        this.setLanguageId(languageId);
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
