package com.daniel.delivery.main.repository.delivery;

import com.daniel.delivery.main.repository.delivery.DeliveryRepositoryEJB;
import com.daniel.delivery.main.repository.delivery.DeliveryRepositoryJPA;
import com.daniel.delivery.abstraction.entity.Delivery;
import com.daniel.delivery.abstraction.entity.FoodProduct;
import com.daniel.delivery.abstraction.entity.Item;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DeliveryRepositoryEJBTest {
    @Mock
    private DeliveryRepositoryJPA deliveryRepositoryJPA;
    
    @InjectMocks
    private DeliveryRepositoryEJB deliveryRepositoryEJB;

    @Test
    public void save() {
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        Item item = new Item(1, 1, foodProduct);
        Delivery delivery = new Delivery(1, "Street 50", "555233564", 23600, 100, "email5@email.com", "PENDING", Arrays.asList(item));

        deliveryRepositoryEJB.save(delivery);
        
        verify(deliveryRepositoryJPA).save(delivery);
    }
    
    @Test
    public void update() {
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        Item item = new Item(1, 1, foodProduct);
        Delivery delivery = new Delivery(1, "Street 50", "555233564", 23600, 100, "email5@email.com", "PENDING", Arrays.asList(item));

        deliveryRepositoryEJB.update(delivery);
        
        verify(deliveryRepositoryJPA).update(delivery);
    }
    
    @Test
    public void getAll() {
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        Item item = new Item(1, 1, foodProduct);
        Delivery delivery = new Delivery(1, "Street 50", "555233564", 23600, 100, "email5@email.com", "PENDING", Arrays.asList(item));

        when(deliveryRepositoryJPA.getAll()).thenReturn(Arrays.asList(delivery));
                
        List<Delivery> deliveries = deliveryRepositoryEJB.getAll();
        
        assertThat(deliveries).isEqualTo(Arrays.asList(delivery));
    }
    
     @Test
    public void getByEmailAndState() {
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        Item item = new Item(1, 1, foodProduct);
        Delivery delivery = new Delivery(1, "Street 50", "555233564", 23600, 100, "email5@email.com", "PENDING", Arrays.asList(item));

        when(deliveryRepositoryJPA.getByEmailAndState("email5@email.com", "PENDING")).thenReturn(Arrays.asList(delivery));
                
        List<Delivery> deliveries = deliveryRepositoryEJB.getByEmailAndState("email5@email.com", "PENDING");
        
        assertThat(deliveries).isEqualTo(Arrays.asList(delivery));
    }
    
}
