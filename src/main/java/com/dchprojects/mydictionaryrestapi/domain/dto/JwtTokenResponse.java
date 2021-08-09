package com.dchprojects.mydictionaryrestapi.domain.dto;

import org.joda.time.DateTime;

public class JwtTokenResponse {

    private String accessToken;
    private DateTime expirationDate;

    public JwtTokenResponse(String accessToken,
                            DateTime expirationDate) {
        this.accessToken = accessToken;
        this.expirationDate = expirationDate;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public DateTime getExpirationDate() {
        return expirationDate;
    }

}
