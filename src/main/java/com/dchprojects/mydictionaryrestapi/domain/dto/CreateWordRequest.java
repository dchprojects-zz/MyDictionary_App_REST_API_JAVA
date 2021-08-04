package com.dchprojects.mydictionaryrestapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateWordRequest {

    @NotBlank
    @JsonProperty("user_id")
    private Long userId;

    @NotBlank
    @JsonProperty("word_id")
    private Long wordId;

    @NotBlank
    @JsonProperty("course_id")
    private Long courseId;

    @NotBlank
    @JsonProperty("language_id")
    private Long languageId;

    @NotBlank
    @JsonProperty("word_text")
    private String wordText;

    @NotBlank
    @JsonProperty("word_description")
    private String wordDescription;

    @NotBlank
    @JsonProperty("language_name")
    private String languageName;

}
