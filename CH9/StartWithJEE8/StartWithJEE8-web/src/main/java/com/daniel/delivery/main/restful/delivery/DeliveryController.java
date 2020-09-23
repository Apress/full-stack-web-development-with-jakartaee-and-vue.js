
package com.daniel.delivery.main.restful.delivery;

import com.daniel.delivery.abstraction.service.delivery.DeliveryService;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Stateless
@LocalBean
@Path("deliveries")
public class DeliveryController{
    @Inject
    private DeliveryService deliveryService;
 
    @GET    
    @Path("{email}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<DeliveryDTO> getByEmailAndState(@PathParam("email") String email, @QueryParam("state") String state) {
        return deliveryService.getByEmailAndState(email, state)
                .stream()
                .map(d -> new DeliveryDTO(d))
                .collect(Collectors.toList());
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public DeliveryDTO request(DeliveryDTO delivery) {
        return new DeliveryDTO(deliveryService.request(delivery.toDelivery()));
    }
}
