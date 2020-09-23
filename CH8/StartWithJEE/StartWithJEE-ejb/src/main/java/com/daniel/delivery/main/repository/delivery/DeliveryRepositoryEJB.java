package com.daniel.delivery.main.repository.delivery;

import com.daniel.delivery.abstraction.entity.Delivery;
import com.daniel.delivery.abstraction.repository.DeliveryRepository;
import com.daniel.delivery.main.Infrastructure;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Local
@Infrastructure
public class DeliveryRepositoryEJB implements DeliveryRepository{
    @Inject
    private DeliveryRepositoryJPA deliveryRepositoryJPA;

    @Override
    public List<Delivery> getAll() {
        return deliveryRepositoryJPA.getAll();
    }

    @Override
    public Delivery save(Delivery delivery) {
        return deliveryRepositoryJPA.save(delivery);
    }

    @Override
    public Delivery update(Delivery delivery) {
        return deliveryRepositoryJPA.update(delivery);
    }

    @Override
    public List<Delivery> getByEmailAndState(String email, String state) {
        return deliveryRepositoryJPA.getByEmailAndState(email, state);
    }
}
