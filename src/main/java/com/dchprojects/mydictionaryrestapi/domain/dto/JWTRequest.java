package com.dchprojects.mydictionaryrestapi.domain.dto;

public class JWTRequest {

    private Long userId;

    private String nickname;

    private String password;

    public JWTRequest(Long userId,
                      String nickname,
                      String password) {

        this.userId = userId;
        this.nickname = nickname;
        this.password = password;

    }

    public Long getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

}
