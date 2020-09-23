package com.daniel.delivery.abstraction.repository;

import com.daniel.delivery.abstraction.entity.FoodProduct;
import java.util.List;
import java.util.Optional;

public interface FoodProductRepository extends Repository<FoodProduct>{
    List<FoodProduct> getByFoodService(String email);
    Optional<FoodProduct> getById(Integer id);
}
