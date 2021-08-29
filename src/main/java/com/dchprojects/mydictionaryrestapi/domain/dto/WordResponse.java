package com.dchprojects.mydictionaryrestapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class WordResponse {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("word_id")
    private Long wordId;

    @JsonProperty("course_id")
    private Long courseId;

    @JsonProperty("language_id")
    private Long languageId;

    @JsonProperty("word_text")
    private String wordText;

    @JsonProperty("word_description")
    private String wordDescription;

    @JsonProperty("language_name")
    private String languageName;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    public WordResponse() {

    }

    public WordResponse(Long userId,
                        Long wordId,
                        Long courseId,
                        Long languageId,
                        String wordText,
                        String wordDescription,
                        String languageName,
                        Timestamp createdAt) {

        this.setUserId(userId);
        this.setWordId(wordId);
        this.setCourseId(courseId);
        this.setLanguageId(languageId);
        this.setWordText(wordText);
        this.setWordDescription(wordDescription);
        this.setLanguageName(languageName);
        this.setCreatedAt(createdAt);

    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getWordId() {
        return wordId;
    }

    public String getWordDescription() {
        return wordDescription;
    }

    public String getWordText() {
        return wordText;
    }

    public String getLanguageName() {
        return languageName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setWordDescription(String wordDescription) {
        this.wordDescription = wordDescription;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    public void setWordText(String wordText) {
        this.wordText = wordText;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
