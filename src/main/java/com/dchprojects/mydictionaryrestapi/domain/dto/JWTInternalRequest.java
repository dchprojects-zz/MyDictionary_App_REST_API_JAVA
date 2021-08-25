package com.dchprojects.mydictionaryrestapi.domain.dto;

public class JWTInternalRequest {

    private Long userId;

    private String nickname;

    private String passwordFromRequest;

    private String databasePassword;

    public JWTInternalRequest(Long userId,
                              String nickname,
                              String passwordFromRequest,
                              String databasePassword) {

        this.setUserId(userId);
        this.setNickname(nickname);
        this.setPasswordFromRequest(passwordFromRequest);
        this.setDatabasePassword(databasePassword);

    }

    public Long getUserId() {
        return userId;
    }

    public String getPasswordFromRequest() {
        return passwordFromRequest;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public String getNickname() {
        return nickname;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPasswordFromRequest(String passwordFromRequest) {
        this.passwordFromRequest = passwordFromRequest;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
