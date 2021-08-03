package com.dchprojects.mydictionaryrestapi.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateNicknameRequest {

    @NotNull
    private String nickname;

}
