
package com.daniel.delivery.main.repository.delivery;

import com.daniel.delivery.abstraction.entity.Delivery;
import com.daniel.delivery.abstraction.entity.FoodProduct;
import com.daniel.delivery.abstraction.entity.Item;
import com.daniel.delivery.abstraction.repository.DeliveryRepository;
import com.daniel.delivery.main.Infrastructure;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class DeliveryRepositoryEJBIT {
    
    @Inject
    @Infrastructure
    private DeliveryRepository deliveryRepository;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "com.daniel.delivery")
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("META-INF/deliverydata.sql", "META-INF/data.sql")
                .addAsManifestResource("beans.xml", "beans.xml");
    }

    @Test
    @InSequence(1)
    public void getAll_basicData_same() {
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        Item item = new Item(1, 1, foodProduct);
        Delivery delivery = new Delivery(1, "Street 50", "555233564", 23600, 100, "email5@email.com", "PENDING", Arrays.asList(item));

        List<Delivery> deliveries = deliveryRepository.getAll();

        assertThat(deliveries).isEqualTo(Arrays.asList(delivery));
    }
    
    @Test
    @InSequence(2)
    public void save_new_getAll() {
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        
        Item item1 = new Item(1, 1, foodProduct);
        Delivery delivery1 = new Delivery(1, "Street 50", "555233564", 23600, 100, "email5@email.com", "PENDING", Arrays.asList(item1));
        
        Item newItem = new Item(null, 2, foodProduct);
        Delivery newDelivery = new Delivery(null, "Street 89", "55587412", 20100, 100, "email10@email.com", "PENDING", Arrays.asList(newItem));
        
        Item expectedItem = new Item(2, 2, foodProduct);
        Delivery expectedDelivery = new Delivery(2, "Street 89", "55587412", 20100, 100, "email10@email.com", "PENDING", Arrays.asList(expectedItem));

        newDelivery = deliveryRepository.save(newDelivery);
        
        List<Delivery> deliveries = deliveryRepository.getAll();

        assertThat(deliveries).isEqualTo(Arrays.asList(delivery1, expectedDelivery));
    }
    
    @Test
    @InSequence(3)
    public void update_state_updated() {
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        
        Item item1 = new Item(1, 1, foodProduct);
        Delivery delivery1 = new Delivery(1, "Street 50", "555233564", 23600, 100, "email5@email.com", "PENDING", Arrays.asList(item1));

        Item expectedItem = new Item(2, 2, foodProduct);
        Delivery expectedDelivery = new Delivery(2, "Street 89", "55587412", 20100, 100, "email10@email.com", "DONE", Arrays.asList(expectedItem));

        Delivery delivery = deliveryRepository.update(expectedDelivery);
        
        List<Delivery> deliveries = deliveryRepository.getAll();

        assertThat(deliveries).isEqualTo(Arrays.asList(delivery1, expectedDelivery));
    }
    
    @Test
    @InSequence(4)
    public void getDeliveriesByEmailAndState_emailAndState_list() {
        FoodProduct foodProduct = new FoodProduct(1, "Pizza", 23500, "Pinaple Pizza", true, "imageUrl", "email1@email.com");
        Item item = new Item(1, 1, foodProduct);
        Delivery delivery = new Delivery(1, "Street 50", "555233564", 23600, 100, "email5@email.com", "PENDING", Arrays.asList(item));

        List<Delivery> deliveries = deliveryRepository.getByEmailAndState("email5@email.com", "PENDING");

        assertThat(deliveries).isEqualTo(Arrays.asList(delivery));
    }
}
