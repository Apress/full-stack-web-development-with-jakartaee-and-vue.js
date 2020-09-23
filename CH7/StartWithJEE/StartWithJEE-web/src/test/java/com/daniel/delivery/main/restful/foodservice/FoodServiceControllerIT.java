
package com.daniel.delivery.main.restful.foodservice;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.assertj.core.api.Assertions.assertThat;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class FoodServiceControllerIT {
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        Path persistence = Paths.get("../StartWithJEE-ejb/src/test/resources/META-INF/persistence.xml");
        Path deliveryData = Paths.get("../StartWithJEE-ejb/src/test/resources/META-INF/foodservicedata.sql");

        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "com.daniel.delivery")
                .addAsResource(persistence.toFile(), "META-INF/persistence.xml")
                .addAsResource(deliveryData.toFile(), "META-INF/data.sql");
    }

    @Test
    @RunAsClient
    @InSequence(1)
    public void getByFoodType(@ArquillianResteasyResource("api") WebTarget webTarget) {
        UserDTO user = new UserDTO("email1@email.com", "pass1");
        FoodServiceDTO foodService = new FoodServiceDTO("email1@email.com", "Pizzas 25", "Street 89", "PIZZA", 100, true, user, Collections.emptyList());
        
        Response response = webTarget
                .path("foodservices")
                .queryParam("foodType", "PIZZA")
                .request(MediaType.APPLICATION_JSON)
                .get();
        
        List<FoodServiceDTO> foodServices = response.readEntity(new GenericType<List<FoodServiceDTO>>() { });
        
        assertThat(foodServices).isEqualTo(Arrays.asList(foodService));
    }
    
    @Test
    @RunAsClient
    @InSequence(2)
    public void save(@ArquillianResteasyResource("api") WebTarget webTarget) {    
        UserDTO user = new UserDTO("chicken@email.com", "pass2");
        FoodServiceDTO foodService = new FoodServiceDTO("chicken@email.com", "Chicken Cool", "Street 898", "CHICKEN", 120, true, user, Collections.emptyList());
        
        UserDTO userExpected = new UserDTO("chicken@email.com", "pass2");
        FoodServiceDTO foodServiceExpected = new FoodServiceDTO("chicken@email.com", "Chicken Cool", "Street 898", "CHICKEN", 120, true, userExpected, Collections.emptyList());
        
        Response response = webTarget
                .path("foodservices")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(foodService, MediaType.APPLICATION_JSON));
        
        foodService = response.readEntity(FoodServiceDTO.class);
        
        assertThat(foodService).isEqualTo(foodServiceExpected);
    }
    
    @Test
    @RunAsClient
    @InSequence(3)
    public void update(@ArquillianResteasyResource("api") WebTarget webTarget) {    
        UserDTO user = new UserDTO("chicken@email.com", "pass2");
        FoodServiceDTO foodService = new FoodServiceDTO("chicken@email.com", "Chicken Cool Other", "Street 898", "CHICKEN", 120, true, user, Collections.emptyList());
        
        UserDTO userExpected = new UserDTO("chicken@email.com", "pass2");
        FoodServiceDTO foodServiceExpected = new FoodServiceDTO("chicken@email.com", "Chicken Cool Other", "Street 898", "CHICKEN", 120, true, userExpected, Collections.emptyList());
         
        Response response = webTarget
                .path("foodservices")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(foodService, MediaType.APPLICATION_JSON));
        
        foodService = response.readEntity(FoodServiceDTO.class);
        
        assertThat(foodService).isEqualTo(foodServiceExpected);
    }
    
    @Test
    @RunAsClient
    @InSequence(4)
    public void deActivate(@ArquillianResteasyResource("api") WebTarget webTarget) {        
        UserDTO user = new UserDTO("chicken@email.com", "pass2");
        FoodServiceDTO foodService = new FoodServiceDTO("chicken@email.com", "Chicken Cool Other", "Street 898", "CHICKEN", 120, true, user, Collections.emptyList());
        
        UserDTO userExpected = new UserDTO("chicken@email.com", "pass2");
        FoodServiceDTO foodServiceExpected = new FoodServiceDTO("chicken@email.com", "Chicken Cool Other", "Street 898", "CHICKEN", 120, false, userExpected, Collections.emptyList());
        
        Response response = webTarget
                .path("foodservices")
                .path("chicken@email.com")
                .request(MediaType.APPLICATION_JSON)
                .delete();
        
        foodService = response.readEntity(FoodServiceDTO.class);
        
        assertThat(foodService).isEqualTo(foodServiceExpected);
    }
}
