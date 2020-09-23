
package com.daniel.delivery.abstraction.service.security;

public class AuthorizationException extends RuntimeException{
    public AuthorizationException(String string) {
        super(string);
    }
    
    public AuthorizationException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
}
