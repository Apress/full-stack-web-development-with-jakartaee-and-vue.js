
package com.daniel.delivery.abstraction.service.foodservice;

import com.daniel.delivery.abstraction.entity.FoodService;
import com.daniel.delivery.abstraction.entity.User;
import com.daniel.delivery.abstraction.repository.FoodServiceRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FoodServiceServiceBasic implements FoodServiceService{
    private final FoodServiceRepository foodServiceRepository;
    
    public FoodServiceServiceBasic(FoodServiceRepository foodServiceRepository){
        this.foodServiceRepository = foodServiceRepository;
    }
    
    @Override
    public FoodService save(FoodService foodService) {
        return foodServiceRepository.save(foodService);
    }

    @Override
    public FoodService update(FoodService foodService) {
        return foodServiceRepository.update(foodService);
    }

    @Override
    public FoodService deActivate(String email) {
        FoodService foodService = foodServiceRepository.getById(email)
                .orElseThrow(IllegalArgumentException::new);
        
        foodService.deActivate();
        
        return foodServiceRepository.update(foodService);
    }

    @Override
    public List<FoodService> getByFoodType(String foodType, Integer page, Integer pageSize) {
        List<FoodService> foodServices = Collections.emptyList();
        
        if("ALL".equals(foodType)){
            foodServices = foodServiceRepository.getAll(page, pageSize);
        }else{
            foodServices = foodServiceRepository.getByFoodType(foodType, page, pageSize);            
        }
        
        return foodServices.stream()
                .filter(FoodService::getActive)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FoodService> login(User user) {
        return foodServiceRepository.getByEmailAndPassword(user.getEmail(), user.getPassword())
                .filter(FoodService::getActive);
        
    }

    @Override
    public Optional<FoodService> getById(String email) {
        return foodServiceRepository.getById(email);
    }
    
}

