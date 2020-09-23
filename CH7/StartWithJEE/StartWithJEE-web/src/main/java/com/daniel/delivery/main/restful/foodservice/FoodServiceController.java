
package com.daniel.delivery.main.restful.foodservice;

import com.daniel.delivery.abstraction.service.foodservice.FoodServiceService;
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

@Stateless
@LocalBean
@Path("foodservices")
public class FoodServiceController{
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
    public List<FoodServiceDTO> getByFoodType(@QueryParam("foodType") String foodType) {
        return foodServiceService.getByFoodType(foodType)
                .stream()
                .map(f -> new FoodServiceDTO(f))
                .collect(Collectors.toList());
    }
}
