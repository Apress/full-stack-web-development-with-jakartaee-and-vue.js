package com.daniel.delivery.main.security.foodservice.keycloak;

import com.daniel.delivery.abstraction.entity.FoodService;
import com.daniel.delivery.abstraction.entity.User;
import com.daniel.delivery.abstraction.repository.FoodServiceRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;

public class KeyCloakFoodServiceRepository implements FoodServiceRepository {

    private final Keycloak keycloak;
    private final String realm;

    public KeyCloakFoodServiceRepository(Keycloak keycloak, String realm) {
        this.keycloak = keycloak;
        this.realm = realm;
    }

    @Override
    public FoodService save(FoodService foodService) {
        Response response = keycloak
                .realm(realm)
                .users()
                .create(convertFoodServiceToUserRepresentation(foodService));

        if (response.getStatus() != 200) {
            throw new IllegalArgumentException("Food service " + foodService.getEmail() + " couldn't be saved in KeyCloak: " + response.readEntity(String.class));
        }

        return foodService;
    }

    @Override
    public FoodService update(FoodService foodService) {
        UserResource userResource = keycloak
                .realm(realm)
                .users()
                .get(foodService.getId());
        
        userResource.update(convertFoodServiceToUserRepresentation(foodService));

        return foodService;
    }

    @Override
    public List<FoodService> getAll() {
        return keycloak
                .realm(realm)
                .users()
                .list()
                .stream()
                .map(this::convertUserRepresentationToFoodService)
                .collect(Collectors.toList());
    }

    @Override
    public List<FoodService> getAll(Integer page, Integer pageSize) {
        return keycloak
                .realm(realm)
                .users()
                .list((page - 1) * pageSize, pageSize)
                .stream()
                .map(this::convertUserRepresentationToFoodService)
                .collect(Collectors.toList());
    }

    @Override
    public List<FoodService> getByFoodType(String foodType, Integer page, Integer pageSize) {
        return keycloak
                .realm(realm)
                .users()
                .list()
                .stream()
                .map(this::convertUserRepresentationToFoodService)
                .filter(foodService -> foodService.getFoodType().equals(foodType))
                .limit(pageSize)
                .skip((page - 1) * pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FoodService> getById(String id) {
        return Optional.ofNullable(keycloak
                .realm(realm)
                .users()
                .get(id))
                .map(userResource -> userResource.toRepresentation())
                .map(this::convertUserRepresentationToFoodService);
    }

    @Override
    public Optional<FoodService> getByEmailAndPassword(String email, String password) {
        throw new UnsupportedOperationException();
    }

    private UserRepresentation convertFoodServiceToUserRepresentation(FoodService foodService) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setId(foodService.getId());
        userRepresentation.setEmail(foodService.getEmail());
        userRepresentation.setUsername(foodService.getEmail());
        userRepresentation.setFirstName(foodService.getName());
        userRepresentation.setEnabled(foodService.getActive());

        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("foodType", Arrays.asList(foodService.getFoodType()));
        attributes.put("deliveryFee", Arrays.asList(String.valueOf(foodService.getDeliveryFee())));
        attributes.put("imageUrl", Arrays.asList(foodService.getImageUrl()));
        attributes.put("address", Arrays.asList(foodService.getAddress()));

        userRepresentation.setAttributes(attributes);

        return userRepresentation;
    }

    private FoodService convertUserRepresentationToFoodService(UserRepresentation userRepresentation) {
        User user = new User(userRepresentation.getEmail(), userRepresentation.getEmail());

        return new FoodService(userRepresentation.getId(), userRepresentation.getEmail(), userRepresentation.getFirstName(), getAttribute(userRepresentation.getAttributes(), "address"), getAttribute(userRepresentation.getAttributes(), "imageUrl"), getAttribute(userRepresentation.getAttributes(), "foodType"), Integer.parseInt(Optional.ofNullable(getAttribute(userRepresentation.getAttributes(), "deliveryFee")).filter(s -> !s.equals("")).orElse("0")), userRepresentation.isEnabled(), user, Collections.emptyList());
    }

    private String getAttribute(Map<String, List<String>> attributes, String name) {
        return Optional.ofNullable(attributes)
                .map(att -> att.get(name))
                .orElse(Collections.emptyList())
                .stream()
                .findFirst()
                .orElse("");
    }
}
