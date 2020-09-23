package com.daniel.delivery.main.repository;

import com.daniel.delivery.main.repository.delivery.DeliveryRepositoryJPA;
import com.daniel.delivery.main.repository.foodproduct.FoodProductRepositoryJPA;
import com.daniel.delivery.main.repository.foodservice.FoodServiceRepositoryJPA;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class RepositoryProvider {
    @PersistenceContext
    private EntityManager em;

    @Produces
    public EntityManager getEntityManager() {
        return em;
    }
    
    @Produces
    public FoodServiceRepositoryJPA getFoodServiceRepositoryJPA(EntityManager entityManager) {
        return new FoodServiceRepositoryJPA(entityManager);
    }
    
    @Produces
    public DeliveryRepositoryJPA getDeliveryRepositoryJPA(EntityManager entityManager) {
        return new DeliveryRepositoryJPA(entityManager);
    }
    
    @Produces
    public FoodProductRepositoryJPA getFoodProductRepositoryJPA(EntityManager entityManager) {
        return new FoodProductRepositoryJPA(entityManager);
    }
}
