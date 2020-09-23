package com.daniel.delivery.main.repository.delivery;

import com.daniel.delivery.main.repository.delivery.ItemData;
import com.daniel.delivery.main.repository.delivery.DeliveryData;
import com.daniel.delivery.main.repository.delivery.DeliveryRepositoryJPA;
import com.daniel.delivery.abstraction.entity.Delivery;
import com.daniel.delivery.abstraction.entity.FoodProduct;
import com.daniel.delivery.abstraction.entity.Item;
import com.daniel.delivery.main.repository.foodproduct.FoodProductData;
import com.daniel.delivery.main.repository.foodservice.FoodServiceData;
import java.util.Arrays;
import java.util.List;
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
public class DeliveryRepositoryJPATest {

    @Mock
    private EntityManager entityManager;
    
    @Mock
    private TypedQuery typedQuery;
    
    @InjectMocks
    private DeliveryRepositoryJPA deliveryRepositoryJPA;
    
    @Test
    public void save() {
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        Item item = new Item(1, 1, foodProduct);
        Delivery delivery = new Delivery(1, "Street 50", "555233564", 23600, 100, "email5@email.com", "PENDING", Arrays.asList(item));

        FoodServiceData foodServiceData = new FoodServiceData();
        foodServiceData.setEmail("email1@email.com");
        
        FoodProductData foodProductData = new FoodProductData();
        foodProductData.setId(foodProduct.getId());
        foodProductData.setActive(true);
        foodProductData.setDescription(foodProduct.getDescription());
        foodProductData.setImageUrl(foodProduct.getImageUrl());
        foodProductData.setName(foodProduct.getName());
        foodProductData.setPrice(foodProduct.getPrice());
        foodProductData.setFoodService(foodServiceData);
        
        ItemData itemData = new ItemData();
        itemData.setAmount(item.getAmount());
        itemData.setId(item.getId());
        itemData.setFoodProduct(foodProductData);
        
        DeliveryData deliveryData = new DeliveryData();
        deliveryData.setId(delivery.getId());
        deliveryData.setFee(delivery.getFee());
        deliveryData.setPhone(delivery.getPhone());
        deliveryData.setAddress(delivery.getAddress());
        deliveryData.setTotal(delivery.getTotal());
        deliveryData.setEmail(delivery.getEmail());
        deliveryData.setState(delivery.getState());
        deliveryData.setItemList(Arrays.asList(itemData));
        
        when(entityManager.find(FoodProductData.class, 1)).thenReturn(foodProductData);
                
        deliveryRepositoryJPA.save(delivery);
        
        verify(entityManager).persist(deliveryData);
        verify(entityManager).flush();
    }
    
    @Test
    public void update() {
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        Item item = new Item(1, 1, foodProduct);
        Delivery delivery = new Delivery(1, "Street 50", "555233564", 23600, 100, "email5@email.com", "PENDING", Arrays.asList(item));

        FoodServiceData foodServiceData = new FoodServiceData();
        foodServiceData.setEmail("email1@email.com");
        
        FoodProductData foodProductData = new FoodProductData();
        foodProductData.setId(foodProduct.getId());
        foodProductData.setActive(true);
        foodProductData.setDescription(foodProduct.getDescription());
        foodProductData.setImageUrl(foodProduct.getImageUrl());
        foodProductData.setName(foodProduct.getName());
        foodProductData.setPrice(foodProduct.getPrice());
        foodProductData.setFoodService(foodServiceData);
        
        ItemData itemData = new ItemData();
        itemData.setAmount(item.getAmount());
        itemData.setId(item.getId());
        itemData.setFoodProduct(foodProductData);
        
        DeliveryData deliveryData = new DeliveryData();
        deliveryData.setId(delivery.getId());
        deliveryData.setFee(delivery.getFee());
        deliveryData.setPhone(delivery.getPhone());
        deliveryData.setAddress(delivery.getAddress());
        deliveryData.setTotal(delivery.getTotal());
        deliveryData.setEmail(delivery.getEmail());
        deliveryData.setState(delivery.getState());
        deliveryData.setItemList(Arrays.asList(itemData));
        
        when(entityManager.merge(deliveryData)).thenReturn(deliveryData);
        when(entityManager.find(FoodProductData.class, 1)).thenReturn(foodProductData);
                
        deliveryRepositoryJPA.update(delivery);
        
        verify(entityManager).merge(deliveryData);
    }
    
    @Test
    public void getAll() {
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        Item item = new Item(1, 1, foodProduct);
        Delivery delivery = new Delivery(1, "Street 50", "555233564", 23600, 100, "email5@email.com", "PENDING", Arrays.asList(item));

        FoodServiceData foodServiceData = new FoodServiceData();
        foodServiceData.setEmail("email1@email.com");
        
        FoodProductData foodProductData = new FoodProductData();
        foodProductData.setId(foodProduct.getId());
        foodProductData.setActive(true);
        foodProductData.setDescription(foodProduct.getDescription());
        foodProductData.setImageUrl(foodProduct.getImageUrl());
        foodProductData.setName(foodProduct.getName());
        foodProductData.setPrice(foodProduct.getPrice());
        foodProductData.setFoodService(foodServiceData);
        
        ItemData itemData = new ItemData();
        itemData.setAmount(item.getAmount());
        itemData.setId(item.getId());
        itemData.setFoodProduct(foodProductData);
        
        DeliveryData deliveryData = new DeliveryData();
        deliveryData.setId(delivery.getId());
        deliveryData.setFee(delivery.getFee());
        deliveryData.setPhone(delivery.getPhone());
        deliveryData.setAddress(delivery.getAddress());
        deliveryData.setTotal(delivery.getTotal());
        deliveryData.setEmail(delivery.getEmail());
        deliveryData.setState(delivery.getState());
        deliveryData.setItemList(Arrays.asList(itemData));
        
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(deliveryData));
        when(entityManager.createNamedQuery("DeliveryData.findAll", DeliveryData.class)).thenReturn(typedQuery);
        when(entityManager.find(FoodProductData.class, 1)).thenReturn(foodProductData);
                
        List<Delivery> deliveries = deliveryRepositoryJPA.getAll();
        
        assertThat(deliveries).isEqualTo(Arrays.asList(delivery));
   
    }
    
    @Test
    public void getByEmailAndState() {
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        Item item = new Item(1, 1, foodProduct);
        Delivery delivery = new Delivery(1, "Street 50", "555233564", 23600, 100, "email5@email.com", "PENDING", Arrays.asList(item));

        FoodServiceData foodServiceData = new FoodServiceData();
        foodServiceData.setEmail("email1@email.com");
        
        FoodProductData foodProductData = new FoodProductData();
        foodProductData.setId(foodProduct.getId());
        foodProductData.setActive(true);
        foodProductData.setDescription(foodProduct.getDescription());
        foodProductData.setImageUrl(foodProduct.getImageUrl());
        foodProductData.setName(foodProduct.getName());
        foodProductData.setPrice(foodProduct.getPrice());
        foodProductData.setFoodService(foodServiceData);
        
        ItemData itemData = new ItemData();
        itemData.setAmount(item.getAmount());
        itemData.setId(item.getId());
        itemData.setFoodProduct(foodProductData);
        
        DeliveryData deliveryData = new DeliveryData();
        deliveryData.setId(delivery.getId());
        deliveryData.setFee(delivery.getFee());
        deliveryData.setPhone(delivery.getPhone());
        deliveryData.setAddress(delivery.getAddress());
        deliveryData.setTotal(delivery.getTotal());
        deliveryData.setEmail(delivery.getEmail());
        deliveryData.setState(delivery.getState());
        deliveryData.setItemList(Arrays.asList(itemData));
        
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(deliveryData));
        when(entityManager.createNamedQuery("DeliveryData.findByEmailAndState", DeliveryData.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("email", "email5@email.com")).thenReturn(typedQuery);
        when(typedQuery.setParameter("state", "PENDING")).thenReturn(typedQuery);
        when(entityManager.find(FoodProductData.class, 1)).thenReturn(foodProductData);
                
        List<Delivery> deliveries = deliveryRepositoryJPA.getByEmailAndState("email5@email.com", "PENDING");
        
        assertThat(deliveries).isEqualTo(Arrays.asList(delivery));
   
    }
    
}
