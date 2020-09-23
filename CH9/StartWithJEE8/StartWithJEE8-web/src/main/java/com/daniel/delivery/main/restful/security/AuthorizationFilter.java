package com.daniel.delivery.main.restful.security;

import com.daniel.delivery.abstraction.service.security.TokenValidationService;
import java.io.IOException;
import java.util.Optional;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

@Provider
@Stateless
@LocalBean
@RequiredAuthorization
public class AuthorizationFilter implements ContainerRequestFilter {
    @Inject
    private TokenValidationService tokenValidationService;
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationHeader = Optional.ofNullable(requestContext.getHeaderString(HttpHeaders.AUTHORIZATION))
                .orElseThrow(() -> new NotAuthorizedException("Authorization header not found"));
 
        String token = authorizationHeader.substring("Bearer".length()).trim();
        
        tokenValidationService.validate(token);
    }
}
