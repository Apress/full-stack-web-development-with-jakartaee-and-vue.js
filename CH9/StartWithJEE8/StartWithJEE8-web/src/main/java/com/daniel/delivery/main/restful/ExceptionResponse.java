
package com.daniel.delivery.main.restful;

public class ExceptionResponse {
    private final String message;
    private final String stackTrace;
    
    public ExceptionResponse (String message, String stackTrace){
        this.message = message;
        this.stackTrace = stackTrace;
    }

    public String getMessage() {
        return message;
    }

    public String getStackTrace() {
        return stackTrace;
    }
}
