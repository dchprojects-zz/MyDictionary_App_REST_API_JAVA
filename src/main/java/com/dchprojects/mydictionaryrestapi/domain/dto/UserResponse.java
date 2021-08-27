package com.dchprojects.mydictionaryrestapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("nickname")
    private String nickname;

    public UserResponse(Long userId,
                        String nickname) {

        this.setUserId(userId);
        this.setNickname(nickname);

    }

    public String getNickname() {
        return nickname;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
