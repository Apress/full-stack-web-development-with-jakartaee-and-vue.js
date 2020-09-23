package com.daniel.delivery.main.repository;

import com.daniel.delivery.main.Testing;
import com.daniel.delivery.main.repository.delivery.DeliveryRepositoryJPA;
import com.daniel.delivery.main.repository.foodproduct.FoodProductRepositoryJPA;
import com.daniel.delivery.main.security.foodservice.keycloak.MockFoodServiceRepository;
import com.daniel.delivery.main.security.foodservice.keycloak.KeyCloakFoodServiceRepository;
import java.util.Properties;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.keycloak.admin.client.Keycloak;

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
    public DeliveryRepositoryJPA getDeliveryRepositoryJPA(EntityManager entityManager) {
        return new DeliveryRepositoryJPA(entityManager);
    }

    @Produces
    public FoodProductRepositoryJPA getFoodProductRepositoryJPA(EntityManager entityManager) {
        return new FoodProductRepositoryJPA(entityManager);
    }

    @Produces
    public KeyCloakFoodServiceRepository getKeyCloakFoodServiceRepository(Properties properties) {
        Keycloak keycloak = Keycloak.getInstance(
                properties.getProperty("SSO_AUTH_URL"),
                "master",
                properties.getProperty("SSO_AUTH_USER"),
                properties.getProperty("SSO_AUTH_PASSWORD"),
                "admin-cli");
        
        return new KeyCloakFoodServiceRepository(keycloak, properties.getProperty("SSO_REALM"));
    }
    
    @Produces
    @Testing
    public MockFoodServiceRepository getMockFoodServiceRepository() {
        return new MockFoodServiceRepository();
    }
}
