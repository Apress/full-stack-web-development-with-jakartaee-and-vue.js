package com.daniel.delivery.main.repository.foodproduct;

import com.daniel.delivery.abstraction.entity.FoodProduct;
import com.daniel.delivery.abstraction.repository.FoodProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;

public class FoodProductRepositoryJPA implements FoodProductRepository {

    private final EntityManager entityManager;

    public FoodProductRepositoryJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public FoodProduct save(FoodProduct foodProduct) {
        FoodProductData foodProductData = convertFoodProductToFoodProductData(foodProduct);

        entityManager.persist(foodProductData);
        entityManager.flush();

        return convertFoodProductDataToFoodProduct(foodProductData);
    }

    @Override
    public FoodProduct update(FoodProduct delivery) {
        FoodProductData foodProductData = convertFoodProductToFoodProductData(delivery);

        foodProductData = entityManager.merge(foodProductData);

        return convertFoodProductDataToFoodProduct(foodProductData);
    }

    @Override
    public List<FoodProduct> getAll() {
        return entityManager.createNamedQuery("FoodProductData.findAll", FoodProductData.class)
                .getResultList()
                .stream()
                .map(this::convertFoodProductDataToFoodProduct)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<FoodProduct> getByFoodService(String foodService, Integer page, Integer pageSize) {
        return entityManager.createNamedQuery("FoodProductData.findByFoodService", FoodProductData.class)
                .setParameter("foodService", foodService)
                .setMaxResults(pageSize)
                .setFirstResult((page - 1) * pageSize)
                .getResultList()
                .stream()
                .map(this::convertFoodProductDataToFoodProduct)
                .collect(Collectors.toList());
    }    

    @Override
    public Optional<FoodProduct> getById(Integer id) {
        return Optional.ofNullable(entityManager.find(FoodProductData.class, id))
                .map(this::convertFoodProductDataToFoodProduct);
    }
    
    private FoodProductData convertFoodProductToFoodProductData(FoodProduct foodProduct) {        
        FoodProductData foodProductData = new FoodProductData();
        foodProductData.setId(foodProduct.getId());
        foodProductData.setActive(foodProduct.isActive());
        foodProductData.setDescription(foodProduct.getDescription());
        foodProductData.setImageUrl(foodProduct.getImageUrl());
        foodProductData.setName(foodProduct.getName());
        foodProductData.setPrice(foodProduct.getPrice());
        foodProductData.setFoodService(foodProduct.getFoodService());
        
        return foodProductData;        
    }

    private FoodProduct convertFoodProductDataToFoodProduct(FoodProductData foodProductData) {
        return new FoodProduct(foodProductData.getId(), foodProductData.getName(), foodProductData.getPrice(), foodProductData.getDescription(), foodProductData.getActive(), foodProductData.getImageUrl(), foodProductData.getFoodService());
    }
}
