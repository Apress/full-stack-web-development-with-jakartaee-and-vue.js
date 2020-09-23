package com.daniel.delivery.main.security;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import com.nimbusds.jwt.proc.JWTProcessor;
import com.daniel.delivery.abstraction.service.security.NullObjectTokenValidation;
import com.daniel.delivery.abstraction.service.security.TokenValidationService;
import com.daniel.delivery.main.Testing;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;

@Stateless
@LocalBean
public class SecurityProvider {

    @Produces
    public JWTProcessor getJWTProcessor(Properties properties) throws MalformedURLException {
        // Set up a JWT processor to parse the tokens and then check their signature
        // and validity time window (bounded by the "iat", "nbf" and "exp" claims)
        ConfigurableJWTProcessor jwtProcessor = new DefaultJWTProcessor();

        // The public RSA keys to validate the signatures will be sourced from the
        // OAuth 2.0 server's JWK set, published at a well-known URL. The RemoteJWKSet
        // object caches the retrieved keys to speed up subsequent look-ups and can
        // also gracefully handle key-rollover
        JWKSource keySource = new RemoteJWKSet(new URL(properties.getProperty("SSO_JWK_URL")));

        // The expected JWS algorithm of the access tokens (agreed out-of-band)
        JWSAlgorithm expectedJWSAlg = JWSAlgorithm.RS256;

        // Configure the JWT processor with a key selector to feed matching public
        // RSA keys sourced from the JWK set URL
        JWSKeySelector keySelector = new JWSVerificationKeySelector(expectedJWSAlg, keySource);
        jwtProcessor.setJWSKeySelector(keySelector);

        return jwtProcessor;
    }
        
    @Produces
    public TokenValidationService getTokenValidationService(JWTProcessor jwtProcessor) {
        return new TokenValidationJWK(jwtProcessor);
    }
    
    @Produces
    @Testing
    public TokenValidationService getNullObjectTokenValidation() {
        return new NullObjectTokenValidation();
    }
}
