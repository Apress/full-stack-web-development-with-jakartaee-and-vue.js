package com.daniel.delivery.main.repository.foodservice;

import com.daniel.delivery.main.repository.foodservice.FoodServiceRepositoryEJB;
import com.daniel.delivery.main.repository.foodservice.FoodServiceRepositoryJPA;
import com.daniel.delivery.abstraction.entity.FoodService;
import com.daniel.delivery.abstraction.entity.User;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class FoodServiceRepositoryEJBTest {
    @Mock
    private FoodServiceRepositoryJPA foodServiceRepositoryJPA;
    
    @InjectMocks
    private FoodServiceRepositoryEJB foodServiceRepositoryEJB;
    
    @Test
    public void save() {
        User user = new User("email1@email.com", "pass1");
        FoodService foodService = new FoodService("email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, user, Collections.emptyList());
        
        foodServiceRepositoryEJB.save(foodService);
        
        verify(foodServiceRepositoryJPA).save(foodService);
    }
    
    @Test
    public void update() {
        User user = new User("email1@email.com", "pass1");
        FoodService foodService = new FoodService("email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, user, Collections.emptyList());
        
        foodServiceRepositoryEJB.update(foodService);
        
        verify(foodServiceRepositoryJPA).update(foodService);
    }
    
    @Test
    public void getAll() {
        User user = new User("email1@email.com", "pass1");
        FoodService foodService = new FoodService("email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, user, Collections.emptyList());
        
        when(foodServiceRepositoryJPA.getAll()).thenReturn(Arrays.asList(foodService));
                
        List<FoodService> foodServices = foodServiceRepositoryEJB.getAll();
        
        assertThat(foodServices).isEqualTo(Arrays.asList(foodService));
    }
    
    @Test
    public void getAllPagination() {
        User user = new User("email1@email.com", "pass1");
        FoodService foodService = new FoodService("email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, user, Collections.emptyList());
        
        when(foodServiceRepositoryJPA.getAll(2, 20)).thenReturn(Arrays.asList(foodService));
                
        List<FoodService> foodServices = foodServiceRepositoryEJB.getAll(2, 20);
        
        assertThat(foodServices).isEqualTo(Arrays.asList(foodService));
    }
    
    @Test
    public void getByFoodType() {
        User user = new User("email1@email.com", "pass1");
        FoodService foodService = new FoodService("email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, user, Collections.emptyList());
        
        when(foodServiceRepositoryJPA.getByFoodType("PIZZA", 2, 20)).thenReturn(Arrays.asList(foodService));
                
        List<FoodService> foodServices = foodServiceRepositoryEJB.getByFoodType("PIZZA", 2, 20);
        
        assertThat(foodServices).isEqualTo(Arrays.asList(foodService));
    }
    
    @Test
    public void getById() {
        User user = new User("email1@email.com", "pass1");
        FoodService foodService = new FoodService("email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, user, Collections.emptyList());
        
        when(foodServiceRepositoryJPA.getById("email1@email.com")).thenReturn(Optional.of(foodService));
                
        Optional<FoodService> foodServiceResult = foodServiceRepositoryEJB.getById("email1@email.com");
        
        assertThat(foodServiceResult.get()).isEqualTo(foodService);
    }
    
    @Test
    public void getByEmailAndPassword() {
        User user = new User("email1@email.com", "pass1");
        FoodService foodService = new FoodService("email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, user, Collections.emptyList());
        
        when(foodServiceRepositoryJPA.getByEmailAndPassword("email1@email.com", "pass1")).thenReturn(Optional.of(foodService));
                
        Optional<FoodService> foodServiceResult = foodServiceRepositoryEJB.getByEmailAndPassword("email1@email.com", "pass1");
        
        assertThat(foodServiceResult.get()).isEqualTo(foodService);
    }
}
