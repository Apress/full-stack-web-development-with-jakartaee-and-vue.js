package com.daniel.delivery.main.restful.files;

import com.daniel.delivery.abstraction.service.file.StorageService;
import java.io.IOException;
import java.io.InputStream;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import com.daniel.delivery.main.restful.security.RequiredAuthorization;

@Stateless
@LocalBean
@Path("files")
public class FileController {
    @Context
    private UriInfo uriInfo;
    @Inject
    private StorageService storageService;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiredAuthorization
    public ImageDTO uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
        String fileKey = storageService.save(fileDetail.getFileName(), uploadedInputStream);
                
        return new ImageDTO(uriInfo.getBaseUri() + "files/" + fileKey);
    }
    
    @Path("{fileKey}")
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    public InputStream loadFile(@PathParam("fileKey") String fileKey) throws IOException {
        return storageService.load(fileKey);
    }
}
