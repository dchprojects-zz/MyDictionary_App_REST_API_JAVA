package com.dchprojects.mydictionaryrestapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseResponse {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("course_id")
    private Long courseId;

    @JsonProperty("language_id")
    private Long languageId;

    @JsonProperty("language_name")
    private String languageName;

    public CourseResponse(Long userId,
                          Long courseId,
                          Long languageId,
                          String languageName) {

        this.setUserId(userId);
        this.setCourseId(courseId);
        this.setLanguageId(languageId);
        this.setLanguageName(languageName);

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

}
