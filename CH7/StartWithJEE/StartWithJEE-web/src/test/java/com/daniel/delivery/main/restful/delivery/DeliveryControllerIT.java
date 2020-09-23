package com.daniel.delivery.main.restful.delivery;

import com.daniel.delivery.main.restful.foodproduct.FoodProductDTO;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;
import java.util.Arrays;
import javax.ws.rs.client.Entity;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class DeliveryControllerIT {

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        Path persistence = Paths.get("../StartWithJEE-ejb/src/test/resources/META-INF/persistence.xml");
        Path deliveryData = Paths.get("../StartWithJEE-ejb/src/test/resources/META-INF/deliverydata.sql");

        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "com.daniel.delivery")
                .addAsResource(persistence.toFile(), "META-INF/persistence.xml")
                .addAsResource(deliveryData.toFile(), "META-INF/data.sql");
    }

    @Test
    @RunAsClient
    @InSequence(1)
    public void getDeliveriesByEmailAndState_emailAndState_list(@ArquillianResteasyResource("api") WebTarget webTarget) {
        FoodProductDTO foodProduct = new FoodProductDTO(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        ItemDTO item = new ItemDTO(1, 1, foodProduct);
        DeliveryDTO delivery = new DeliveryDTO(1, "Street 50", "555233564", 23600, 100, "email5@email.com", "PENDING", Arrays.asList(item));

        Response response = webTarget
                .path("deliveries")
                .path("email5@email.com")
                .queryParam("state", "PENDING")
                .request(MediaType.APPLICATION_JSON)
                .get();
        
        List<DeliveryDTO> deliveries = response.readEntity(new GenericType<List<DeliveryDTO>>() { });
        
        assertThat(deliveries).isEqualTo(Arrays.asList(delivery));
    }
    
    @Test
    @RunAsClient
    @InSequence(2)
    public void request(@ArquillianResteasyResource("api") WebTarget webTarget) {
        FoodProductDTO foodProduct = new FoodProductDTO(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        
        ItemDTO newItem = new ItemDTO(null, 2, foodProduct);
        DeliveryDTO newDelivery = new DeliveryDTO(null, "Street 89", "55587412", 20100, 100, "email10@email.com", "PENDING", Arrays.asList(newItem));
        
        ItemDTO expectedItem = new ItemDTO(2, 2, foodProduct);
        DeliveryDTO expectedDelivery = new DeliveryDTO(2, "Street 89", "55587412", 20100, 100, "email10@email.com", "PENDING", Arrays.asList(expectedItem));

        Response response = webTarget
                .path("deliveries")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(newDelivery, MediaType.APPLICATION_JSON));
        
        newDelivery = response.readEntity(DeliveryDTO.class);
        
        assertThat(newDelivery).isEqualTo(expectedDelivery);
    }
}
