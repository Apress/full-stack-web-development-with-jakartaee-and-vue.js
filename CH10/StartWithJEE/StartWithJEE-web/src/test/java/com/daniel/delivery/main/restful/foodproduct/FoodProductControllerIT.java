
package com.daniel.delivery.main.restful.foodproduct;

import com.daniel.delivery.main.restful.foodproduct.FoodProductDTO;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
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
public class FoodProductControllerIT {
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        Path persistence = Paths.get("../StartWithJEE-ejb/src/test/resources/META-INF/persistence.xml");
        Path deliveryData = Paths.get("../StartWithJEE-ejb/src/test/resources/META-INF/foodproductdata.sql");
        Path beansTest = Paths.get("../StartWithJEE-ejb/src/test/resources/beans.xml");

        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "com.daniel.delivery")
                .addAsResource(persistence.toFile(), "META-INF/persistence.xml")
                .addAsResource(deliveryData.toFile(), "META-INF/data.sql")
                .addAsManifestResource(beansTest.toFile(), "beans.xml");
    }

    @Test
    @RunAsClient
    @InSequence(1)
    public void getByFoodService(@ArquillianResteasyResource("api") WebTarget webTarget) {
        FoodProductDTO foodProduct1 = new FoodProductDTO(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        
        Response response = webTarget
                .path("foodproducts")
                .queryParam("foodService", "email1@email.com")
                .queryParam("page", "1")
                .queryParam("pageSize", "20")
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer XXXXXXXXX")
                .get();
        
        List<FoodProductDTO> foodProducts = response.readEntity(new GenericType<List<FoodProductDTO>>() { });
        
        assertThat(foodProducts).isEqualTo(Arrays.asList(foodProduct1));
    }
    
    @Test
    @RunAsClient
    @InSequence(2)
    public void save(@ArquillianResteasyResource("api") WebTarget webTarget) {        
        FoodProductDTO newFoodProduct = new FoodProductDTO(null, "Pizza", 23500, "Cheese Pizza", true, "imageUrl2", "email1@email.com");
        
        FoodProductDTO expectedFoodProduct = new FoodProductDTO(2, "Pizza", 23500, "Cheese Pizza", true, "imageUrl2", "email1@email.com");
        
        Response response = webTarget
                .path("foodproducts")
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer XXXXXXXXX")
                .post(Entity.entity(newFoodProduct, MediaType.APPLICATION_JSON));
        
        newFoodProduct = response.readEntity(FoodProductDTO.class);
        
        assertThat(newFoodProduct).isEqualTo(expectedFoodProduct);
    }
    
    @Test
    @RunAsClient
    @InSequence(3)
    public void update(@ArquillianResteasyResource("api") WebTarget webTarget) {        
        FoodProductDTO updateFoodProduct = new FoodProductDTO(2, "Pizza", 23500, "Cheese Pizza Old", true, "imageUrl2", "email1@email.com");
        
        FoodProductDTO expectedFoodProduct = new FoodProductDTO(2, "Pizza", 23500, "Cheese Pizza Old", true, "imageUrl2", "email1@email.com");
        
        Response response = webTarget
                .path("foodproducts")
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer XXXXXXXXX")
                .put(Entity.entity(updateFoodProduct, MediaType.APPLICATION_JSON));
        
        updateFoodProduct = response.readEntity(FoodProductDTO.class);
        
        assertThat(updateFoodProduct).isEqualTo(expectedFoodProduct);
    }
    
    @Test
    @RunAsClient
    @InSequence(4)
    public void deActivate(@ArquillianResteasyResource("api") WebTarget webTarget) {        
        FoodProductDTO updateFoodProduct = new FoodProductDTO(2, "Pizza", 23500, "Cheese Pizza Old", true, "imageUrl2", "email1@email.com");
        
        FoodProductDTO expectedFoodProduct = new FoodProductDTO(2, "Pizza", 23500, "Cheese Pizza Old", false, "imageUrl2", "email1@email.com");
        
        Response response = webTarget
                .path("foodproducts")
                .path("2")
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer XXXXXXXXX")
                .delete();
        
        updateFoodProduct = response.readEntity(FoodProductDTO.class);
        
        assertThat(updateFoodProduct).isEqualTo(expectedFoodProduct);
    }
    
    @Test
    @RunAsClient
    @InSequence(5)
    public void getById(@ArquillianResteasyResource("api") WebTarget webTarget) {
        FoodProductDTO foodProduct1 = new FoodProductDTO(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        
        Response response = webTarget
                .path("foodproducts")
                .path("1")
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer XXXXXXXXX")
                .get();
        
        FoodProductDTO foodProduct = response.readEntity(FoodProductDTO.class);
        
        assertThat(foodProduct).isEqualTo(foodProduct1);
    }
}
