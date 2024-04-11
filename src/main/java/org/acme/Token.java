package org.acme;

public class Token {
    private String accesstoken;
    private String refreshToken;

    // Constructors
    public Token() {
    }

    public Token(String accesstoken, String refreshToken) {
        this.accesstoken = accesstoken;
        this.refreshToken = refreshToken;
    }

    // Getters and setters
    public String getaccesstoken() {
        return accesstoken;
    }

    public void setaccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public String getrefreshToken() {
        return refreshToken;
    }

    public void setrefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    
}
