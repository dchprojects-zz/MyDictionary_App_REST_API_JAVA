package com.dchprojects.mydictionaryrestapi.domain.dto;

public class JWTInternalRequest {

    private Long userId;

    private String nickname;

    private String password;

    public JWTInternalRequest(Long userId,
                              String nickname,
                              String password) {

        this.setUserId(userId);
        this.setNickname(nickname);
        this.setPassword(password);

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

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
