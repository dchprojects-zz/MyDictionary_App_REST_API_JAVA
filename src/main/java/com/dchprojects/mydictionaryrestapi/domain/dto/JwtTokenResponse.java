package com.dchprojects.mydictionaryrestapi.domain.dto;

import java.util.Date;

public class JwtTokenResponse {

    private String accessToken;
    private Date expirationDate;

    public JwtTokenResponse(String accessToken,
                            Date expirationDate) {
        this.accessToken = accessToken;
        this.expirationDate = expirationDate;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

}
