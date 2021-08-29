package com.dchprojects.mydictionaryrestapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class CourseResponse {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("course_id")
    private Long courseId;

    @JsonProperty("language_id")
    private Long languageId;

    @JsonProperty("language_name")
    private String languageName;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    public CourseResponse() {

    }

    public CourseResponse(Long userId,
                          Long courseId,
                          Long languageId,
                          String languageName,
                          Timestamp createdAt) {

        this.setUserId(userId);
        this.setCourseId(courseId);
        this.setLanguageId(languageId);
        this.setLanguageName(languageName);
        this.setCreatedAt(createdAt);

    }

    public Long getUserId() {
        return userId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
