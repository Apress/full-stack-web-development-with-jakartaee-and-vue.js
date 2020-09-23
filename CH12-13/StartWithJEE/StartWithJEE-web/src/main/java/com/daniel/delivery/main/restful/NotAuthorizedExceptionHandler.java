package com.daniel.delivery.main.restful;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotAuthorizedExceptionHandler implements ExceptionMapper<NotAuthorizedException> {

    private final Logger logger = Logger.getLogger(NotAuthorizedExceptionHandler.class.getName());

    @Override
    public Response toResponse(NotAuthorizedException e) {
        logger.log(Level.SEVERE, e.getMessage(), e);

        StringWriter stringWriter = new StringWriter();

        e.printStackTrace(new PrintWriter(stringWriter));

        return Response.status(401)
                .entity(new ExceptionResponse(e.getMessage(), e.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
