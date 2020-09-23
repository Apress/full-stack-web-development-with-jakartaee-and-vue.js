package com.daniel.delivery.abstraction.service.delivery;

import com.daniel.delivery.abstraction.entity.Delivery;
import com.daniel.delivery.abstraction.repository.DeliveryRepository;
import java.util.List;

public class DeliveryServiceBasic implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryServiceBasic(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public List<Delivery> getByEmailAndState(String email, String state) {
        return deliveryRepository.getByEmailAndState(email, state);
    }

    @Override
    public Delivery request(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }
}
