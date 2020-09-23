
package com.daniel.delivery.main.restful.security;

import com.daniel.delivery.abstraction.entity.Token;
import java.util.Objects;

public class TokenDTO {
    private final String userId;
    private final String userName;
    private final String userEmail;
    private final String accessToken;
    private final String refreshToken;
    private final String expiresIn;

    public TokenDTO(Token token) {
        this.userId = token.getUserId();
        this.userName = token.getUserName();
        this.userEmail = token.getUserEmail();
        this.accessToken = token.getAccessToken();
        this.refreshToken = token.getRefreshToken();
        this.expiresIn = token.getExpiresIn();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.userId);
        hash = 83 * hash + Objects.hashCode(this.userName);
        hash = 83 * hash + Objects.hashCode(this.userEmail);
        hash = 83 * hash + Objects.hashCode(this.accessToken);
        hash = 83 * hash + Objects.hashCode(this.refreshToken);
        hash = 83 * hash + Objects.hashCode(this.expiresIn);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TokenDTO other = (TokenDTO) obj;
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.userEmail, other.userEmail)) {
            return false;
        }
        if (!Objects.equals(this.accessToken, other.accessToken)) {
            return false;
        }
        if (!Objects.equals(this.refreshToken, other.refreshToken)) {
            return false;
        }
        if (!Objects.equals(this.expiresIn, other.expiresIn)) {
            return false;
        }
        return true;
    }


    
}
