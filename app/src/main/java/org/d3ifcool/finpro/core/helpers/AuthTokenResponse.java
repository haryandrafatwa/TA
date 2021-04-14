package org.d3ifcool.finpro.core.helpers;

import com.google.gson.annotations.SerializedName;

public class AuthTokenResponse extends BaseResponse {
    @SerializedName("token_type")
    private String tokenType = "";

    @SerializedName("expires_in")
    private int expiresIn = 0;

    @SerializedName("access_token")
    private String accessToken = "";

    @SerializedName("refresh_token")
    private String refreshToken = "";

    public AuthTokenResponse(String tokenType, int expiresIn, String accessToken, String refreshToken) {
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public AuthTokenResponse() {
    }

    public String getTokenType() {
        return tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}

