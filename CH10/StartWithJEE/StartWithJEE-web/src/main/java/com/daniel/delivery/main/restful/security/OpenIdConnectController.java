package com.daniel.delivery.main.restful.security;

import com.daniel.delivery.abstraction.service.security.OpenIdConnectService;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@LocalBean
@Path("openidconnect")
public class OpenIdConnectController {

    @Inject
    private OpenIdConnectService openIdConnectService;

    @POST
    @Path("token")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    public TokenDTO save(@FormParam("grant_type") String grantType, @FormParam("code") String code, @FormParam("redirect_uri") String redirectUrl) {
        return new TokenDTO(openIdConnectService.requestToken(grantType, code, redirectUrl));
    }
}
