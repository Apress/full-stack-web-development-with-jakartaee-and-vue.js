package com.daniel.delivery.main.restful;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GeneralExceptionHandler implements ExceptionMapper<Exception> {

    private final Logger logger = Logger.getLogger(GeneralExceptionHandler.class.getName());

    @Override
    public Response toResponse(Exception e) {
        logger.log(Level.SEVERE, e.getMessage(), e);

        StringWriter stringWriter = new StringWriter();

        e.printStackTrace(new PrintWriter(stringWriter));

        return Response.status(500)
                .entity(new ExceptionResponse(e.getMessage(), stringWriter.toString()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
