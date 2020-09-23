package com.daniel.delivery.main.repository.delivery;

import com.daniel.delivery.abstraction.entity.Delivery;
import com.daniel.delivery.abstraction.entity.FoodProduct;
import com.daniel.delivery.abstraction.entity.Item;
import com.daniel.delivery.abstraction.repository.DeliveryRepository;
import com.daniel.delivery.main.repository.foodproduct.FoodProductData;
import com.daniel.delivery.main.repository.foodservice.FoodServiceData;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;

public class DeliveryRepositoryJPA implements DeliveryRepository {

    private final EntityManager entityManager;

    public DeliveryRepositoryJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Delivery save(Delivery delivery) {
        DeliveryData deliveryData = convertDeliveryToDeliveryData(delivery);

        entityManager.persist(deliveryData);

        return convertDeliveryDataToDelivery(deliveryData);
    }

    @Override
    public Delivery update(Delivery delivery) {
        DeliveryData deliveryData = convertDeliveryToDeliveryData(delivery);

        deliveryData = entityManager.merge(deliveryData);

        return convertDeliveryDataToDelivery(deliveryData);
    }

    @Override
    public List<Delivery> getAll() {
        return entityManager.createNamedQuery("DeliveryData.findAll", DeliveryData.class)
                .getResultList()
                .stream()
                .map(this::convertDeliveryDataToDelivery)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Delivery> getByEmailAndState(String email, String state) {
        return entityManager.createNamedQuery("DeliveryData.findByEmailAndState", DeliveryData.class)
                .setParameter("state", state)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .map(this::convertDeliveryDataToDelivery)
                .collect(Collectors.toList());
    }    

    private DeliveryData convertDeliveryToDeliveryData(Delivery delivery) {
        DeliveryData deliveryData = new DeliveryData();
        deliveryData.setId(delivery.getId());
        deliveryData.setFee(delivery.getFee());
        deliveryData.setPhone(delivery.getPhone());
        deliveryData.setAddress(delivery.getAddress());
        deliveryData.setTotal(delivery.getTotal());
        deliveryData.setEmail(delivery.getEmail());
        deliveryData.setState(delivery.getState());
        deliveryData.setItemList(delivery.getItemList()
                .stream()
                .map(this::convertItemToItemData)
                .collect(Collectors.toList()));
        return deliveryData;
    }

    private ItemData convertItemToItemData(Item item) {
        FoodProductData foodProductData = new FoodProductData();
        foodProductData.setId(item.getFoodProduct().getId());

        ItemData itemData = new ItemData();
        itemData.setId(item.getId());
        itemData.setAmount(item.getAmount());
        itemData.setFoodProduct(foodProductData);

        return itemData;
    }

    private Delivery convertDeliveryDataToDelivery(DeliveryData deliveryData) {
        List<Item> items = deliveryData.getItemList()
                .stream()
                .map(this::convertItemDataToItem)
                .collect(Collectors.toList());

        return new Delivery(deliveryData.getId(), deliveryData.getAddress(), deliveryData.getPhone(), deliveryData.getTotal(), deliveryData.getFee(), deliveryData.getEmail(), deliveryData.getState(), items);
    }

    private Item convertItemDataToItem(ItemData itemData) {
        FoodProductData foodProductData = itemData.getFoodProduct();
        
        foodProductData = entityManager.find(FoodProductData.class, foodProductData.getId());
        
        FoodServiceData foodServiceData = foodProductData.getFoodService();

        FoodProduct foodProduct = new FoodProduct(foodProductData.getId(), foodProductData.getName(), foodProductData.getPrice(), foodProductData.getDescription(), foodProductData.getActive(), foodProductData.getImageUrl(), foodServiceData.getEmail());

        return new Item(itemData.getId(), itemData.getAmount(), foodProduct);
    }

}
