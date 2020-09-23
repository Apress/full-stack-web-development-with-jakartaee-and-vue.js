package com.daniel.delivery.abstraction.repository;

import com.daniel.delivery.abstraction.entity.Delivery;
import java.util.List;

public interface DeliveryRepository extends Repository<Delivery>{
    List<Delivery> getByEmailAndState(String email, String state);
}
