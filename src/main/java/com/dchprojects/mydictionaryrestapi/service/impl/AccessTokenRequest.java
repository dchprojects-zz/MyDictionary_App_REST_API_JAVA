package com.dchprojects.mydictionaryrestapi.service.impl;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AccessTokenRequest {

    @NotNull
    private String nickname;

    @NotNull
    private String password;

}
