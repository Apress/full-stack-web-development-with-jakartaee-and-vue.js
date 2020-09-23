
package com.daniel.delivery.abstraction.service.foodservice;

import com.daniel.delivery.abstraction.service.foodservice.FoodServiceServiceBasic;
import com.daniel.delivery.abstraction.entity.FoodService;
import com.daniel.delivery.abstraction.entity.User;
import com.daniel.delivery.abstraction.repository.FoodServiceRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FoodServiceServiceBasicTest {
    @Mock
    private FoodServiceRepository foodServiceRepository;
    
    @InjectMocks
    private FoodServiceServiceBasic foodServiceServiceBasic;
    
    @Test
    public void getById_found_ok(){
        User user = new User("email1@email.com", "pass1");
        FoodService foodService1 = new FoodService("email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, user, Collections.emptyList());
        
        when(foodServiceRepository.getById("email1@email.com")).thenReturn(Optional.of(foodService1));
        
        Optional<FoodService> foodService = foodServiceRepository.getById("email1@email.com");
        
        assertThat(foodService).isEqualTo(Optional.of(foodService1));
    }
    
    @Test
    public void save_passThrough_ok(){
        User user = new User("email1@email.com", "pass1");
        FoodService foodService = new FoodService("email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, user, Collections.emptyList());
        
        when(foodServiceRepository.save(foodService)).thenReturn(foodService);
        
        FoodService foodService1 = foodServiceServiceBasic.save(foodService);
        
        assertThat(foodService1).isEqualTo(foodService);
    }
    
    @Test
    public void update_passThrough_ok(){
        User user = new User("email1@email.com", "pass1");
        FoodService foodService = new FoodService("email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, user, Collections.emptyList());
        
        when(foodServiceRepository.update(foodService)).thenReturn(foodService);
        
        FoodService foodService1 = foodServiceServiceBasic.update(foodService);
        
        assertThat(foodService1).isEqualTo(foodService);
    }
    
    @Test
    public void deActivate_toFalse_ok(){
        User user = new User("email1@email.com", "pass1");
        FoodService foodService = new FoodService("email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, user, Collections.emptyList());
        
        User userDeactivated = new User("email1@email.com", "pass1");
        FoodService foodServiceDeactivated = new FoodService("email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, false, userDeactivated, Collections.emptyList());
        
        when(foodServiceRepository.getById("email1@email.com")).thenReturn(Optional.ofNullable(foodService));
        when(foodServiceRepository.update(foodService)).thenReturn(foodService);
        
        FoodService foodServiceResult = foodServiceServiceBasic.deActivate("email1@email.com");
        
        assertThat(foodServiceResult).isEqualTo(foodServiceDeactivated);
    }
    
    @Test
    public void getByFoodType_pizza_ok(){
        User userPizzaDeactivated = new User("email3@email.com", "pass1");
        FoodService foodServicePizzaDeactivated = new FoodService("email3@email.com", "Pizzas 55", "Street 79", "imageURL", "PIZZA", 100, false, userPizzaDeactivated, Collections.emptyList());
        
        User userPizza = new User("email1@email.com", "pass1");
        FoodService foodServicePizza = new FoodService("email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, userPizza, Collections.emptyList());
       
        when(foodServiceRepository.getByFoodType("PIZZA", 2, 20)).thenReturn(Arrays.asList(foodServicePizza, foodServicePizzaDeactivated));
        
        List<FoodService> foodServices = foodServiceServiceBasic.getByFoodType("PIZZA", 2, 20);
        
        assertThat(foodServices).isEqualTo(Arrays.asList(foodServicePizza));
    }
    
    @Test
    public void getByFoodType_all_ok(){
        User userPizzaDeactivated = new User("email3@email.com", "pass1");
        FoodService foodServicePizzaDeactivated = new FoodService("email3@email.com", "Pizzas 55", "Street 79", "imageURL", "PIZZA", 100, false, userPizzaDeactivated, Collections.emptyList());
        
        User userPizza = new User("email1@email.com", "pass1");
        FoodService foodServicePizza = new FoodService("email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, userPizza, Collections.emptyList());
        
        User userChicken = new User("email2@email.com", "pass1");
        FoodService foodServiceChicken = new FoodService("email2@email.com", "Chicken 25", "Street 9", "imageURL", "CHICKEN", 100, true, userChicken, Collections.emptyList());
        
        when(foodServiceRepository.getAll(2, 20)).thenReturn(Arrays.asList(foodServicePizza, foodServicePizzaDeactivated, foodServiceChicken));
        
        List<FoodService> foodServices = foodServiceServiceBasic.getByFoodType("ALL", 2, 20);
        
        assertThat(foodServices).isEqualTo(Arrays.asList(foodServicePizza, foodServiceChicken));
    }
    
    @Test
    public void login_active_foodService(){
        User userPizza = new User("email1@email.com", "pass1");
        FoodService foodServicePizza = new FoodService("email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, userPizza, Collections.emptyList());
        
        when(foodServiceRepository.getByEmailAndPassword("email1@email.com", "pass1")).thenReturn(Optional.of(foodServicePizza));
        
        Optional<FoodService> foodService = foodServiceServiceBasic.login(userPizza);
        
        assertThat(foodService.get()).isEqualTo(foodServicePizza);
    }
    
    @Test
    public void login_deActive_empty(){
        User userPizza = new User("email1@email.com", "pass1");
        FoodService foodServicePizza = new FoodService("email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, false, userPizza, Collections.emptyList());
        
        when(foodServiceRepository.getByEmailAndPassword("email1@email.com", "pass1")).thenReturn(Optional.of(foodServicePizza));
        
        Optional<FoodService> foodService = foodServiceServiceBasic.login(userPizza);
        
        assertThat(foodService.isPresent()).isFalse();
    }
}
