package com.daniel.delivery.main.repository.foodproduct;

import com.daniel.delivery.main.repository.foodproduct.FoodProductRepositoryJPA;
import com.daniel.delivery.main.repository.foodproduct.FoodProductData;
import com.daniel.delivery.abstraction.entity.FoodProduct;
import com.daniel.delivery.main.repository.foodservice.FoodServiceData;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FoodProductRepositoryJPATest {
    @Mock
    private EntityManager entityManager;
    
    @Mock
    private TypedQuery typedQuery;
    
    @InjectMocks
    private FoodProductRepositoryJPA foodProductRepositoryJPA;

    @Test
    public void save() {
        FoodProduct newFoodProduct = new FoodProduct(null, "Pizza", 23500, "Cheese Pizza", true, "imageUrl2", "email1@email.com");
        
        FoodProductData foodProductData = new FoodProductData();
        foodProductData.setActive(newFoodProduct.isActive());
        foodProductData.setDescription(newFoodProduct.getDescription());
        foodProductData.setId(newFoodProduct.getId());
        foodProductData.setImageUrl(newFoodProduct.getImageUrl());
        foodProductData.setName(newFoodProduct.getName());
        foodProductData.setPrice(newFoodProduct.getPrice());
        
        foodProductRepositoryJPA.save(newFoodProduct);
        
        verify(entityManager).persist(foodProductData);
        verify(entityManager).flush();
    }
    
    @Test
    public void update() {
        FoodProduct newFoodProduct = new FoodProduct(1, "Pizza", 23500, "Cheese Pizza", true, "imageUrl2", "email1@email.com");
        
        FoodServiceData foodServiceData = new FoodServiceData();
        foodServiceData.setEmail(newFoodProduct.getFoodService());
        
        FoodProductData foodProductData = new FoodProductData();
        foodProductData.setActive(newFoodProduct.isActive());
        foodProductData.setDescription(newFoodProduct.getDescription());
        foodProductData.setId(newFoodProduct.getId());
        foodProductData.setImageUrl(newFoodProduct.getImageUrl());
        foodProductData.setName(newFoodProduct.getName());
        foodProductData.setPrice(newFoodProduct.getPrice());
        foodProductData.setFoodService(foodServiceData);
        
        when(entityManager.merge(foodProductData)).thenReturn(foodProductData);
        
        foodProductRepositoryJPA.update(newFoodProduct);
        
        verify(entityManager).merge(foodProductData);
    }
    
    @Test
    public void getAll() {
        FoodProduct newFoodProduct = new FoodProduct(1, "Pizza", 23500, "Cheese Pizza", true, "imageUrl2", "email1@email.com");
        
        FoodServiceData foodServiceData = new FoodServiceData();
        foodServiceData.setEmail(newFoodProduct.getFoodService());
        
        FoodProductData foodProductData = new FoodProductData();
        foodProductData.setActive(newFoodProduct.isActive());
        foodProductData.setDescription(newFoodProduct.getDescription());
        foodProductData.setId(newFoodProduct.getId());
        foodProductData.setImageUrl(newFoodProduct.getImageUrl());
        foodProductData.setName(newFoodProduct.getName());
        foodProductData.setPrice(newFoodProduct.getPrice());
        foodProductData.setFoodService(foodServiceData);
        
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(foodProductData));
        when(entityManager.createNamedQuery("FoodProductData.findAll", FoodProductData.class)).thenReturn(typedQuery);
                
        List<FoodProduct> foodProducts = foodProductRepositoryJPA.getAll();
        
        assertThat(foodProducts).isEqualTo(Arrays.asList(newFoodProduct));
    }
    
    @Test
    public void getByFoodService() {
        FoodProduct newFoodProduct = new FoodProduct(1, "Pizza", 23500, "Cheese Pizza", true, "imageUrl2", "email1@email.com");
        
        FoodServiceData foodServiceData = new FoodServiceData();
        foodServiceData.setEmail(newFoodProduct.getFoodService());
        
        FoodProductData foodProductData = new FoodProductData();
        foodProductData.setActive(newFoodProduct.isActive());
        foodProductData.setDescription(newFoodProduct.getDescription());
        foodProductData.setId(newFoodProduct.getId());
        foodProductData.setImageUrl(newFoodProduct.getImageUrl());
        foodProductData.setName(newFoodProduct.getName());
        foodProductData.setPrice(newFoodProduct.getPrice());
        foodProductData.setFoodService(foodServiceData);
        
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(foodProductData));
        when(typedQuery.setParameter("email", "email1@email.com")).thenReturn(typedQuery);
        when(typedQuery.setFirstResult(20)).thenReturn(typedQuery);
        when(typedQuery.setMaxResults(20)).thenReturn(typedQuery);
        when(entityManager.createNamedQuery("FoodProductData.findByFoodService", FoodProductData.class)).thenReturn(typedQuery);
                
        List<FoodProduct> foodProducts = foodProductRepositoryJPA.getByFoodService("email1@email.com", 2, 20);
        
        assertThat(foodProducts).isEqualTo(Arrays.asList(newFoodProduct));
    }
    
    @Test
    public void getById() {
        FoodProduct foodProductExpected = new FoodProduct(1, "Pizza", 23500, "Cheese Pizza", true, "imageUrl2", "email1@email.com");
        
        FoodServiceData foodServiceData = new FoodServiceData();
        foodServiceData.setEmail(foodProductExpected.getFoodService());
        
        FoodProductData foodProductData = new FoodProductData();
        foodProductData.setActive(foodProductExpected.isActive());
        foodProductData.setDescription(foodProductExpected.getDescription());
        foodProductData.setId(foodProductExpected.getId());
        foodProductData.setImageUrl(foodProductExpected.getImageUrl());
        foodProductData.setName(foodProductExpected.getName());
        foodProductData.setPrice(foodProductExpected.getPrice());
        foodProductData.setFoodService(foodServiceData);
        
        when(entityManager.find(FoodProductData.class, 1)).thenReturn(foodProductData);
                
        Optional<FoodProduct> foodProduct = foodProductRepositoryJPA.getById(1);
        
        assertThat(foodProduct.get()).isEqualTo(foodProductExpected);
    }
    
}
