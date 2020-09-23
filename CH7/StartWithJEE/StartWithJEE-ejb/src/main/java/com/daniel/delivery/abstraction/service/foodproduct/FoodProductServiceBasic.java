
package com.daniel.delivery.abstraction.service.foodproduct;

import com.daniel.delivery.abstraction.entity.FoodProduct;
import com.daniel.delivery.abstraction.repository.FoodProductRepository;
import java.util.List;
import java.util.stream.Collectors;

public class FoodProductServiceBasic implements FoodProductService{
    private final FoodProductRepository foodProductRepository;

    public FoodProductServiceBasic(FoodProductRepository foodProductRepository) {
        this.foodProductRepository = foodProductRepository;
    }
    
    @Override
    public FoodProduct save(FoodProduct foodProduct) {
        return foodProductRepository.save(foodProduct);
    }

    @Override
    public FoodProduct update(FoodProduct foodProduct) {
        return foodProductRepository.update(foodProduct);
    }

    @Override
    public List<FoodProduct> getByFoodService(String email) {
        return foodProductRepository.getByFoodService(email)
                .stream()
                .filter(FoodProduct::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public FoodProduct deActivate(Integer id) {
        FoodProduct foodProduct = foodProductRepository.getById(id)
                .orElseThrow(IllegalArgumentException::new);
        
        foodProduct.deActivate();
        
        return foodProductRepository.update(foodProduct);
    }
    
}
