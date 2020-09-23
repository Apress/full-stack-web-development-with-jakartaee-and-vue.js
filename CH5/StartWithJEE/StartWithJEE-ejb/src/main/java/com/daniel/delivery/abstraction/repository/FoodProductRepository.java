package com.daniel.delivery.abstraction.repository;

import com.daniel.delivery.abstraction.entity.FoodProduct;
import java.util.List;

public interface FoodProductRepository extends Repository<FoodProduct>{
    List<FoodProduct> getByFoodService(String email);
}
