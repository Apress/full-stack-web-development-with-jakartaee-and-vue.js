
package com.daniel.delivery.abstraction.service.security;

import com.daniel.delivery.abstraction.entity.Token;

public interface OpenIdConnectService {
    Token requestToken(String grantType, String code, String redirectUrl);
}
