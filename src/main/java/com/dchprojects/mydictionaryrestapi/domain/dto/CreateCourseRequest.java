package com.dchprojects.mydictionaryrestapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateCourseRequest {

    @NotBlank
    @JsonProperty("user_id")
    private Long userId;

    @NotBlank
    @JsonProperty("language_id")
    private Long languageId;

    @NotBlank
    @JsonProperty("language_name")
    private String languageName;

}
