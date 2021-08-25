package com.dchprojects.mydictionaryrestapi.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class JWTApiRequest {

    @NotNull
    private String nickname;

    @NotNull
    private String password;

}
