
package com.daniel.delivery.main.restful.foodproduct;

import com.daniel.delivery.abstraction.service.foodproduct.FoodProductService;
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
@Path("foodproducts")
public class FoodProductController{
    @Inject
    private FoodProductService foodProductService;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public FoodProductDTO save(FoodProductDTO foodProduct) {
        return new FoodProductDTO(foodProductService.save(foodProduct.toFoodProduct()));
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public FoodProductDTO update(FoodProductDTO foodProduct) {
        return new FoodProductDTO(foodProductService.update(foodProduct.toFoodProduct()));
    }
    
    @Path("{id}")
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public FoodProductDTO deActivate(@PathParam("id") Integer id) {
        return new FoodProductDTO(foodProductService.deActivate(id));
    }
    
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<FoodProductDTO> getByFoodService(@QueryParam("foodService") String foodService) {
        return foodProductService.getByFoodService(foodService)
                .stream()
                .map(f -> new FoodProductDTO(f))
                .collect(Collectors.toList());
    }
}
