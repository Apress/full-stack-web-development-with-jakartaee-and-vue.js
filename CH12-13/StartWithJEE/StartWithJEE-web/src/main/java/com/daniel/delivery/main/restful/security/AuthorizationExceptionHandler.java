package com.daniel.delivery.main.restful.security;

import com.daniel.delivery.main.restful.ExceptionResponse;
import com.daniel.delivery.abstraction.service.security.AuthorizationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthorizationExceptionHandler implements ExceptionMapper<AuthorizationException> {

    private final Logger logger = Logger.getLogger(AuthorizationExceptionHandler.class.getName());

    @Override
    public Response toResponse(AuthorizationException e) {
        logger.log(Level.WARNING, e.getMessage(), e);

        StringWriter stringWriter = new StringWriter();

        e.printStackTrace(new PrintWriter(stringWriter));

        return Response.status(401)
                .entity(new ExceptionResponse(e.getMessage(), stringWriter.toString()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
