package com.dchprojects.mydictionaryrestapi.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateUserRequest {

    @NotBlank
    private String nickname;
    @NotBlank
    private String password;

}
