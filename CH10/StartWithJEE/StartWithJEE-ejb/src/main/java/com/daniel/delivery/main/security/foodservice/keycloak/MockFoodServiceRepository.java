
package com.daniel.delivery.main.security.foodservice.keycloak;

import com.daniel.delivery.abstraction.entity.FoodService;
import com.daniel.delivery.abstraction.entity.User;
import com.daniel.delivery.abstraction.repository.FoodServiceRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class MockFoodServiceRepository implements FoodServiceRepository{

    @Override
    public List<FoodService> getAll(Integer page, Integer pageSize) {
        User user = new User("email1@email.com", "pass1");
        FoodService foodService = new FoodService("id", "email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, user, Collections.emptyList());
        
        return Arrays.asList(foodService);
    }

    @Override
    public List<FoodService> getByFoodType(String foodType, Integer page, Integer pageSize) {
        User user = new User("email1@email.com", "pass1");
        FoodService foodService = new FoodService("id", "email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, user, Collections.emptyList());
        
        return Arrays.asList(foodService);
    }

    @Override
    public Optional<FoodService> getById(String email) {
        User user = new User("email1@email.com", "pass1");
        FoodService foodService = new FoodService("id", "email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, user, Collections.emptyList());
        
        return Optional.of(foodService);
    }

    @Override
    public Optional<FoodService> getByEmailAndPassword(String email, String password) {
        return null;
    }

    @Override
    public List<FoodService> getAll() {
        User user = new User("email1@email.com", "pass1");
        FoodService foodService = new FoodService("id", "email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, user, Collections.emptyList());
        
        return Arrays.asList(foodService);
    }

    @Override
    public FoodService save(FoodService foodService) {
        return foodService;
    }

    @Override
    public FoodService update(FoodService foodService) {
        return foodService;
    }
    
}
