package com.dchprojects.mydictionaryrestapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LanguageResponse {

    @JsonProperty("language_id")
    private Long languageId;

    @JsonProperty("language_name")
    private String languageName;

    public LanguageResponse() {

    }

    public LanguageResponse(Long languageId,
                            String languageName) {

        this.setLanguageId(languageId);
        this.setLanguageName(languageName);

    }

    public Long getLanguageId() {
        return languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

}
