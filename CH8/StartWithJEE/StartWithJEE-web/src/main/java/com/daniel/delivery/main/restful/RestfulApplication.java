package com.daniel.delivery.main.restful;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("api")
public class RestfulApplication extends ResourceConfig {
    public RestfulApplication(){
        packages("com.daniel.delivery.main.restful").register(MultiPartFeature.class);
    }
}
