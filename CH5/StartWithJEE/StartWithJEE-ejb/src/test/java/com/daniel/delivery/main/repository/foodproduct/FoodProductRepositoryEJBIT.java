package com.daniel.delivery.main.repository.foodproduct;

import com.daniel.delivery.abstraction.entity.FoodProduct;
import com.daniel.delivery.abstraction.repository.FoodProductRepository;
import com.daniel.delivery.main.Infrastructure;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class FoodProductRepositoryEJBIT {
    
    @Inject
    @Infrastructure
    private FoodProductRepository foodProductRepository;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "com.daniel.delivery")
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("META-INF/foodproductdata.sql", "META-INF/data.sql")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    @InSequence(1)
    public void getAll_basicData_same() {
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        
        List<FoodProduct> foodProducts = foodProductRepository.getAll();

        assertThat(foodProducts).isEqualTo(Arrays.asList(foodProduct));
    }
    
    @Test
    @InSequence(2)
    public void save_new_getAll() {
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        
        FoodProduct newFoodProduct = new FoodProduct(null, "Pizza", 23500, "Cheese Pizza", true, "imageUrl2", "email1@email.com");
        
        FoodProduct expectedFoodProduct = new FoodProduct(2, "Pizza", 23500, "Cheese Pizza", true, "imageUrl2", "email1@email.com");
        
        newFoodProduct = foodProductRepository.save(newFoodProduct);
        
        List<FoodProduct> foodProducts = foodProductRepository.getAll();

        assertThat(foodProducts).isEqualTo(Arrays.asList(foodProduct, expectedFoodProduct));
    }
    
    @Test
    @InSequence(3)
    public void update_state_updated() {
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        
        FoodProduct newFoodProduct = new FoodProduct(2, "Pizza", 23500, "Cheese Pizza Old", true, "imageUrl2", "email1@email.com");
        
        FoodProduct expectedFoodProduct = new FoodProduct(2, "Pizza", 23500, "Cheese Pizza Old", true, "imageUrl2", "email1@email.com");
        
        newFoodProduct = foodProductRepository.update(newFoodProduct);
        
        List<FoodProduct> foodProducts = foodProductRepository.getAll();

        assertThat(foodProducts).isEqualTo(Arrays.asList(foodProduct, expectedFoodProduct));
    }
    
    @Test
    @InSequence(4)
    public void getFoodProductsByFoodService_foodService_list() {
        FoodProduct foodProduct1 = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        
        FoodProduct foodProduct2 = new FoodProduct(2, "Pizza", 23500, "Cheese Pizza Old", true, "imageUrl2", "email1@email.com");
        
        List<FoodProduct> foodProducts = foodProductRepository.getByFoodService("email1@email.com");

        assertThat(foodProducts).isEqualTo(Arrays.asList(foodProduct1, foodProduct2));
    }
}
