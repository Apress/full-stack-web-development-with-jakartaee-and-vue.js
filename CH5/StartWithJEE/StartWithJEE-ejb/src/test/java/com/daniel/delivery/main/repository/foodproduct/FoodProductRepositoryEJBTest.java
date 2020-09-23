package com.daniel.delivery.main.repository.foodproduct;

import com.daniel.delivery.abstraction.entity.FoodProduct;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class FoodProductRepositoryEJBTest {
    @Mock
    private FoodProductRepositoryJPA foodProductRepositoryJPA;
    
    @InjectMocks
    private FoodProductRepositoryEJB foodProductRepositoryEJB;
    
    @Test
    public void save() {
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        
        foodProductRepositoryEJB.save(foodProduct);
        
        verify(foodProductRepositoryJPA).save(foodProduct);
    }
    
    @Test
    public void update() {
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        
        foodProductRepositoryEJB.update(foodProduct);
        
        verify(foodProductRepositoryJPA).update(foodProduct);
    }
    
    @Test
    public void getAll() {
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        
        when(foodProductRepositoryJPA.getAll()).thenReturn(Arrays.asList(foodProduct));
                
        List<FoodProduct> foodProducts = foodProductRepositoryEJB.getAll();
        
        assertThat(foodProducts).isEqualTo(Arrays.asList(foodProduct));
    }
    
     @Test
    public void getByEmailAndState() {
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        
        when(foodProductRepositoryJPA.getByFoodService("email1@email.com")).thenReturn(Arrays.asList(foodProduct));
                
        List<FoodProduct> foodProducts = foodProductRepositoryEJB.getByFoodService("email1@email.com");
        
        assertThat(foodProducts).isEqualTo(Arrays.asList(foodProduct));
    }
    
    
}
