package com.daniel.delivery.main.repository.foodservice;

import com.daniel.delivery.abstraction.entity.FoodService;
import com.daniel.delivery.abstraction.repository.FoodServiceRepository;
import com.daniel.delivery.main.Infrastructure;
import java.util.List;
import java.util.Optional;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Infrastructure
@Local
public class FoodServiceRepositoryEJB implements FoodServiceRepository{
    @Inject
    private FoodServiceRepository foodServiceRepository;

    @Override
    public List<FoodService> getAll() {
        return foodServiceRepository.getAll();
    }

    @Override
    public List<FoodService> getAll(Integer page, Integer pageSize) {
        return foodServiceRepository.getAll(page, pageSize);
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
    public List<FoodService> getByFoodType(String foodType, Integer page, Integer pageSize) {
        return foodServiceRepository.getByFoodType(foodType, page, pageSize);
    }

    @Override
    public Optional<FoodService> getById(String email) {
        return foodServiceRepository.getById(email);
    }

    @Override
    public Optional<FoodService> getByEmailAndPassword(String email, String password) {
        return foodServiceRepository.getByEmailAndPassword(email, password);
    }
}
