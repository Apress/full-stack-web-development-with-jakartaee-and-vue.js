package com.daniel.delivery.main.repository.foodservice;

import com.daniel.delivery.abstraction.entity.FoodService;
import com.daniel.delivery.abstraction.entity.User;
import com.daniel.delivery.abstraction.repository.FoodServiceRepository;
import com.daniel.delivery.main.Infrastructure;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public class FoodServiceRepositoryEJBIT {
    
    @Inject
    @Infrastructure
    private FoodServiceRepository foodServiceRepository;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "com.daniel.delivery")
                .addAsManifestResource("beans.xml", "beans.xml");
    }
    
    @Test
    @InSequence(1)
    public void getAll_basicData_same() {
        User user = new User("email1@email.com", "pass1");
        FoodService foodService = new FoodService("id", "email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, user, Collections.emptyList());
        
        List<FoodService> foodServices = foodServiceRepository.getAll();

        assertThat(foodServices).isEqualTo(Arrays.asList(foodService));
    }
    
    @Test
    @InSequence(2)
    public void getAll_pagination_same() {
        User user = new User("email1@email.com", "pass1");
        FoodService foodService = new FoodService("id", "email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, user, Collections.emptyList());
        
        List<FoodService> foodServices = foodServiceRepository.getAll(1, 20);

        assertThat(foodServices).isEqualTo(Arrays.asList(foodService));
    }
    
    @Test
    @InSequence(3)
    public void save_new_getAll() {        
        User userExpected = new User("chicken@email.com", "pass2");
        FoodService foodServiceExpected = new FoodService("id", "chicken@email.com", "Chicken Cool", "Street 898", "imageURL", "CHICKEN", 120, true, userExpected, Collections.emptyList());
        
        FoodService foodService = foodServiceRepository.save(foodServiceExpected);

        assertThat(foodService).isEqualTo(foodServiceExpected);
    }
    
    @Test
    @InSequence(4)
    public void update_state_updated() {
        User userExpected = new User("chicken@email.com", "pass2");
        FoodService foodServiceExpected = new FoodService("id", "chicken@email.com", "Chicken Cool Other", "Street 898", "imageURL", "CHICKEN", 120, true, userExpected, Collections.emptyList());
         
        FoodService foodService = foodServiceRepository.update(foodServiceExpected);

        assertThat(foodService).isEqualTo(foodServiceExpected);
    }
    
    @Test
    @InSequence(5)
    public void getByFoodType_foodType_list() {
        User user = new User("email1@email.com", "pass1");
        FoodService foodService = new FoodService("id", "email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, user, Collections.emptyList());
        
        List<FoodService> foodServices = foodServiceRepository.getByFoodType("PIZZA", 1, 20);

        assertThat(foodServices).isEqualTo(Arrays.asList(foodService));
    }
    
    @Test
    @InSequence(6)
    public void getById_email_foodService() {
        User user = new User("email1@email.com", "pass1");
        FoodService foodService = new FoodService("id", "email1@email.com", "Pizzas 25", "Street 89", "imageURL", "PIZZA", 100, true, user, Collections.emptyList());
        
        Optional<FoodService> foodServiceResult = foodServiceRepository.getById("email1@email.com");

        assertThat(foodServiceResult.get()).isEqualTo(foodService);
    }
}
