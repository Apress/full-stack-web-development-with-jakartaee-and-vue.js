package com.daniel.delivery.main.security;

import com.nimbusds.jwt.proc.JWTProcessor;
import com.daniel.delivery.abstraction.service.security.AuthorizationException;
import com.daniel.delivery.abstraction.service.security.TokenValidationService;
import java.util.Map;

public class TokenValidationJWK implements TokenValidationService {

    private final JWTProcessor jwtProcessor;

    public TokenValidationJWK(JWTProcessor jwtProcessor) {
        this.jwtProcessor = jwtProcessor;
    }

    @Override
    public Map<String, Object> validate(String jwt) {
        try {
            return jwtProcessor.process(jwt, null).getClaims();
        } catch (Exception ex) {
            throw new AuthorizationException("Token validation fails", ex);
        }
    }

}
