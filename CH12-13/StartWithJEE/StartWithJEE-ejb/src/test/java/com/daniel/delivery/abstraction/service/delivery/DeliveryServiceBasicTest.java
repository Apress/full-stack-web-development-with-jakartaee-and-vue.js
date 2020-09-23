
package com.daniel.delivery.abstraction.service.delivery;

import com.daniel.delivery.abstraction.service.delivery.DeliveryServiceBasic;
import com.daniel.delivery.abstraction.entity.Delivery;
import com.daniel.delivery.abstraction.entity.FoodProduct;
import com.daniel.delivery.abstraction.entity.Item;
import com.daniel.delivery.abstraction.repository.DeliveryRepository;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DeliveryServiceBasicTest {
    @Mock
    private DeliveryRepository deliveryRepository;
    
    @InjectMocks
    private DeliveryServiceBasic deliveryServiceBasic;
    
    @Test
    public void getByEmailAndState_passThrough_ok(){
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        Item item = new Item(1, 1, foodProduct);
        Delivery delivery = new Delivery(1, "Street 50", "555233564", 23600, 100, "email5@email.com", "PENDING", Arrays.asList(item));
 
        when(deliveryRepository.getByEmailAndState("email5@email.com", "PENDING")).thenReturn(Arrays.asList(delivery));
        
        List<Delivery> deliveries = deliveryServiceBasic.getByEmailAndState("email5@email.com", "PENDING");
        
        assertThat(deliveries).isEqualTo(Arrays.asList(delivery));
    }
    
    @Test
    public void request_passThrough_ok(){
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        Item item = new Item(1, 1, foodProduct);
        Delivery delivery = new Delivery(1, "Street 50", "555233564", 23600, 100, "email5@email.com", "PENDING", Arrays.asList(item));
 
        when(deliveryRepository.save(delivery)).thenReturn(delivery);
        
        Delivery deliveryResult = deliveryServiceBasic.request(delivery);
        
        assertThat(deliveryResult).isEqualTo(delivery);
    }
    
}
