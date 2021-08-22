package com.dchprojects.mydictionaryrestapi.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class JWTRequest {

    @NotNull
    private Long userId;

    @NotNull
    private String nickname;

    @NotNull
    private String password;

    public JWTRequest(Long userId,
                      String nickname,
                      String password) {

        this.userId = userId;
        this.nickname = nickname;
        this.password = password;

    }

}
