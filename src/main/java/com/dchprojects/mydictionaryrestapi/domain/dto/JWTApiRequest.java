package com.dchprojects.mydictionaryrestapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class JWTApiRequest {

    @NotNull
    @JsonProperty("user_id")
    private Long userId;

    @NotNull
    private String nickname;

    @NotNull
    private String password;

    @NotNull
    @JsonProperty("old_jwt")
    private String oldJWT;

}
