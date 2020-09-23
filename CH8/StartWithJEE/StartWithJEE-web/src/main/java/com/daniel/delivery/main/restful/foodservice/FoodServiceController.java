package com.daniel.delivery.main.restful.foodservice;

import com.daniel.delivery.abstraction.service.foodservice.FoodServiceService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Stateless
@LocalBean
@Path("foodservices")
public class FoodServiceController {

    @Inject
    private FoodServiceService foodServiceService;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public FoodServiceDTO save(FoodServiceDTO foodService) {
        return new FoodServiceDTO(foodServiceService.save(foodService.toFoodService()));
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public FoodServiceDTO update(FoodServiceDTO foodService) {
        return new FoodServiceDTO(foodServiceService.update(foodService.toFoodService()));
    }

    @Path("{email}")
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public FoodServiceDTO deActivate(@PathParam("email") String email) {
        return new FoodServiceDTO(foodServiceService.deActivate(email));
    }

    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<FoodServiceDTO> getByFoodType(@QueryParam("foodType") String foodType, @QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
        return foodServiceService.getByFoodType(foodType, page, pageSize)
                .stream()
                .map(f -> new FoodServiceDTO(f))
                .collect(Collectors.toList());
    }

    @Path("login")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(UserDTO userDTO) {
        return foodServiceService.login(userDTO.toUser())
                .map(f -> new FoodServiceDTO(f))
                .map(Response::ok)
                .orElseGet(Response::noContent)
                .build();
    }

    @Path("{email}")
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("email") String email) {
        return foodServiceService.getById(email)
                .map(f -> new FoodServiceDTO(f))
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }
}
