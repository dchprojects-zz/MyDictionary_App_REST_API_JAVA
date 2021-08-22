package com.dchprojects.mydictionaryrestapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateUserRequest {

    @NotBlank
    @JsonProperty("nickname")
    private String nickname;

    @NotBlank
    @JsonProperty("password")
    private String password;

}
