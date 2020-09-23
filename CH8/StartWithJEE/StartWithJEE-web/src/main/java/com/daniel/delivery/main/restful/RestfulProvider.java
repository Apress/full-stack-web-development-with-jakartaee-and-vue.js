package com.daniel.delivery.main.restful;

import com.daniel.delivery.abstraction.repository.DeliveryRepository;
import com.daniel.delivery.abstraction.repository.FoodProductRepository;
import com.daniel.delivery.abstraction.repository.FoodServiceRepository;
import com.daniel.delivery.abstraction.service.delivery.DeliveryService;
import com.daniel.delivery.abstraction.service.delivery.DeliveryServiceBasic;
import com.daniel.delivery.abstraction.service.file.StorageService;
import com.daniel.delivery.abstraction.service.foodproduct.FoodProductService;
import com.daniel.delivery.abstraction.service.foodproduct.FoodProductServiceBasic;
import com.daniel.delivery.abstraction.service.foodservice.FoodServiceService;
import com.daniel.delivery.abstraction.service.foodservice.FoodServiceServiceBasic;
import com.daniel.delivery.main.Infrastructure;
import com.daniel.delivery.main.storage.disk.DiskStorageService;
import java.util.Properties;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;

@Stateless
@LocalBean
public class RestfulProvider {

    @Produces
    public DeliveryService getDeliveryService(
            @Infrastructure DeliveryRepository deliveryRepository) {
        return new DeliveryServiceBasic(deliveryRepository);
    }
    
    @Produces
    public FoodProductService getFoodProductService(
            @Infrastructure FoodProductRepository foodProductRepository) {
        return new FoodProductServiceBasic(foodProductRepository);
    }
    
    @Produces
    public FoodServiceService getFoodServiceService(
            @Infrastructure FoodServiceRepository foodServiceRepository) {
        return new FoodServiceServiceBasic(foodServiceRepository);
    }
    
    @Produces
    public StorageService getStorageService(Properties properties) {
        return new DiskStorageService(properties.getProperty("STORAGE_PATH"));
    }
}
