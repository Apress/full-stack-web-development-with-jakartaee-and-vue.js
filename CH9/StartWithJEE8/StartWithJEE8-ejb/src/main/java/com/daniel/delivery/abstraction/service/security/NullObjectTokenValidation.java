
package com.daniel.delivery.abstraction.service.security;

import java.util.Collections;
import java.util.Map;

public class NullObjectTokenValidation implements TokenValidationService{

    @Override
    public Map<String, Object> validate(String jwt) {
        return Collections.emptyMap();
    }
    
}
