package com.dchprojects.mydictionaryrestapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JWTResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expiration_date")
    private String expirationDate;

    public JWTResponse(String accessToken,
                       String expirationDate) {

        this.accessToken = accessToken;
        this.expirationDate = expirationDate;

    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getExpirationDate() { return expirationDate; }

}
