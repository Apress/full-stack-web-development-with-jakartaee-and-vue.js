
package com.daniel.delivery.abstraction.service.foodservice;

import com.daniel.delivery.abstraction.entity.FoodService;
import com.daniel.delivery.abstraction.repository.FoodServiceRepository;
import java.util.Collections;
import java.util.List;
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
    public List<FoodService> getByFoodType(String foodType) {
        List<FoodService> foodServices = Collections.emptyList();
        
        if("ALL".equals(foodType)){
            foodServices = foodServiceRepository.getAll();
        }else{
            foodServices = foodServiceRepository.getByFoodType(foodType);            
        }
        
        return foodServices.stream()
                .filter(FoodService::getActive)
                .collect(Collectors.toList());
    }
    
}
