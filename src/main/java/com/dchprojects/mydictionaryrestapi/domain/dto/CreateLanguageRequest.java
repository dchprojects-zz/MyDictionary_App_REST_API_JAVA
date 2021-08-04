package com.dchprojects.mydictionaryrestapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateLanguageRequest {

    @NotBlank
    @JsonProperty("language_name")
    private String languageName;

}
