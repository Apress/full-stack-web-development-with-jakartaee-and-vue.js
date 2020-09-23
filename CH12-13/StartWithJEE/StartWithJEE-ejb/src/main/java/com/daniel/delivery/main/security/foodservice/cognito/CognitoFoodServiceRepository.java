package com.daniel.delivery.main.security.foodservice.cognito;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserResult;
import com.amazonaws.services.cognitoidp.model.AdminGetUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminGetUserResult;
import com.amazonaws.services.cognitoidp.model.AdminUpdateUserAttributesRequest;
import com.amazonaws.services.cognitoidp.model.AdminUpdateUserAttributesResult;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.ListUsersRequest;
import com.amazonaws.services.cognitoidp.model.ListUsersResult;
import com.amazonaws.services.cognitoidp.model.UserType;
import com.daniel.delivery.abstraction.entity.FoodService;
import com.daniel.delivery.abstraction.entity.User;
import com.daniel.delivery.abstraction.repository.FoodServiceRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CognitoFoodServiceRepository implements FoodServiceRepository {

    private final AWSCognitoIdentityProvider awsCognitoIdentityProvider;
    private final String poolId;

    public CognitoFoodServiceRepository(AWSCognitoIdentityProvider awsCognitoIdentityProvider, String poolId) {
        this.awsCognitoIdentityProvider = awsCognitoIdentityProvider;
        this.poolId = poolId;
    }

    @Override
    public FoodService save(FoodService foodService) {
        AdminCreateUserRequest adminCreateUserRequest = new AdminCreateUserRequest();
        adminCreateUserRequest.setUserPoolId(poolId);
        adminCreateUserRequest.setUsername(foodService.getEmail());

        adminCreateUserRequest.setUserAttributes(convertFoodServiceToUserRepresentation(foodService));

        AdminCreateUserResult response = awsCognitoIdentityProvider.adminCreateUser(adminCreateUserRequest);

        return foodService;
    }

    @Override
    public FoodService update(FoodService foodService) {
        AdminUpdateUserAttributesRequest adminUpdateUserAttributesRequest = new AdminUpdateUserAttributesRequest();
        adminUpdateUserAttributesRequest.setUserPoolId(poolId);
        adminUpdateUserAttributesRequest.setUsername(foodService.getEmail());

        adminUpdateUserAttributesRequest.setUserAttributes(convertFoodServiceToUserRepresentation(foodService));

        AdminUpdateUserAttributesResult response = awsCognitoIdentityProvider.adminUpdateUserAttributes(adminUpdateUserAttributesRequest);

        return foodService;
    }

    @Override
    public List<FoodService> getAll() {
        ListUsersRequest listUsers = new ListUsersRequest();
        listUsers.setUserPoolId(poolId);

        ListUsersResult response = awsCognitoIdentityProvider.listUsers(listUsers);

        return response.getUsers()
                .stream()
                .map(this::convertUserRepresentationToFoodService)
                .collect(Collectors.toList());
    }

    @Override
    public List<FoodService> getAll(Integer page, Integer pageSize) {
        ListUsersRequest listUsers = new ListUsersRequest();
        listUsers.setUserPoolId(poolId);

        ListUsersResult response = awsCognitoIdentityProvider.listUsers(listUsers);

        return response.getUsers()
                .stream()
                .limit(pageSize)
                .skip((page - 1) * pageSize)
                .map(this::convertUserRepresentationToFoodService)
                .collect(Collectors.toList());
    }

    @Override
    public List<FoodService> getByFoodType(String foodType, Integer page, Integer pageSize) {
        ListUsersRequest listUsers = new ListUsersRequest();
        listUsers.setUserPoolId(poolId);

        ListUsersResult response = awsCognitoIdentityProvider.listUsers(listUsers);

        return response.getUsers()
                .stream()
                .map(this::convertUserRepresentationToFoodService)
                .filter(foodService -> foodService.getFoodType().equals(foodType))
                .limit(pageSize)
                .skip((page - 1) * pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FoodService> getById(String id) {
        AdminGetUserRequest adminGetUserRequest = new AdminGetUserRequest();
        adminGetUserRequest.setUserPoolId(poolId);
        adminGetUserRequest.setUsername(id);

        AdminGetUserResult response = awsCognitoIdentityProvider.adminGetUser(adminGetUserRequest);

        return Optional.of(convertUserRepresentationToFoodService(id, response.getUserAttributes(), response.isEnabled()));
    }

    @Override
    public Optional<FoodService> getByEmailAndPassword(String email, String password) {
        throw new UnsupportedOperationException();
    }

    private List<AttributeType> convertFoodServiceToUserRepresentation(FoodService foodService) {
        
        AttributeType foodType = new AttributeType();
        foodType.setName("custom:foodType");
        foodType.setValue(foodService.getFoodType());

        AttributeType deliveryFee = new AttributeType();
        deliveryFee.setName("custom:deliveryFee");
        deliveryFee.setValue(String.valueOf(foodService.getDeliveryFee()));

        AttributeType imageUrl = new AttributeType();
        imageUrl.setName("custom:imageUrl");
        imageUrl.setValue(foodService.getImageUrl());

        AttributeType address = new AttributeType();
        address.setName("address");
        address.setValue(foodService.getAddress());

        return Arrays.asList(foodType, deliveryFee, imageUrl, address);
    }

    private FoodService convertUserRepresentationToFoodService(UserType userType) {
        return convertUserRepresentationToFoodService(getAttribute(userType.getAttributes(), "email"), userType.getAttributes(), userType.getEnabled());
    }
    
    private FoodService convertUserRepresentationToFoodService(String userName, List<AttributeType> attributes, Boolean enabled) {
        User user = new User(userName, userName);

        return new FoodService(userName, userName, getAttribute(attributes, "given_name"), getAttribute(attributes, "address"), getAttribute(attributes, "custom:imageUrl"), getAttribute(attributes, "custom:foodType"), Integer.parseInt(Optional.ofNullable(getAttribute(attributes, "custom:deliveryFee")).filter(s -> !s.equals("")).orElse("0")), enabled, user, Collections.emptyList());
    }

    private String getAttribute(List<AttributeType> attributes, String name) {
        return Optional.ofNullable(attributes)
                .orElse(Collections.emptyList())
                .stream()
                .filter(att -> att.getName().equals(name))
                .findFirst()
                .map(AttributeType::getValue)
                .orElse("");
    }
}
