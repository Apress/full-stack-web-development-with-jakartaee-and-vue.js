
package com.daniel.delivery.abstraction.service.security;

import java.util.Map;

public interface TokenValidationService {
    Map<String, Object> validate(String jwt);
}
