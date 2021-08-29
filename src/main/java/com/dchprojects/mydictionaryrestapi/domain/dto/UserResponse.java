package com.dchprojects.mydictionaryrestapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class UserResponse {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    public UserResponse() {

    }

    public UserResponse(Long userId,
                        String nickname,
                        Timestamp createdAt) {

        this.setUserId(userId);
        this.setNickname(nickname);
        this.setCreatedAt(createdAt);

    }

    public String getNickname() {
        return nickname;
    }

    public Long getUserId() {
        return userId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
