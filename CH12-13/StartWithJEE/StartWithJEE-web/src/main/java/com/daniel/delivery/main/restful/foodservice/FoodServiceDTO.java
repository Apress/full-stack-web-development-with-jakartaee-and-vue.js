package com.daniel.delivery.main.restful.foodservice;

import com.daniel.delivery.abstraction.entity.FoodService;
import com.daniel.delivery.main.restful.foodproduct.FoodProductDTO;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class FoodServiceDTO implements Serializable {
    private String id;
    private String email;
    private String name;
    private String address;
    private String foodType;
    private String imageUrl;
    private int deliveryFee;
    private boolean active;
    private UserDTO user;
    private List<FoodProductDTO> foodProductList;

    public FoodServiceDTO(){
        
    }
    
    public FoodServiceDTO(String id, String email, String name, String address, String foodType, String imageUrl, int deliveryFee, boolean active, UserDTO user, List<FoodProductDTO> foodProductList) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.imageUrl = imageUrl;
        this.foodType = foodType;
        this.active = active;
        this.foodProductList = foodProductList;
        this.deliveryFee = deliveryFee;
        this.user = user;
    }
    
    public FoodServiceDTO(FoodService foodService){
        this.id = foodService.getId();
        this.email = foodService.getEmail();
        this.name = foodService.getName();
        this.address = foodService.getAddress();
        this.imageUrl = foodService.getImageUrl();
        this.foodType = foodService.getFoodType();
        this.active = foodService.getActive();
        this.foodProductList = Optional.ofNullable(foodService.getFoodProductList())
                .orElse(Collections.emptyList())
                .stream()
                .map(f -> new FoodProductDTO(f))
                .collect(Collectors.toList());
        this.deliveryFee = foodService.getDeliveryFee();
        this.user = new UserDTO(foodService.getUser());
    }
    
    public FoodService toFoodService(){
        return new FoodService(this.id, this.email, this.name, this.address, this.imageUrl, this.foodType, this.deliveryFee, this.active, this.user.toUser(), Optional.ofNullable(this.foodProductList)
                .orElse(Collections.emptyList())
                .stream()
                .map(FoodProductDTO::toFoodProduct)
                .collect(Collectors.toList()));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(int deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<FoodProductDTO> getFoodProductList() {
        return foodProductList;
    }

    public void setFoodProductList(List<FoodProductDTO> foodProductList) {
        this.foodProductList = foodProductList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.email);
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.address);
        hash = 89 * hash + Objects.hashCode(this.foodType);
        hash = 89 * hash + Objects.hashCode(this.imageUrl);
        hash = 89 * hash + this.deliveryFee;
        hash = 89 * hash + (this.active ? 1 : 0);
        hash = 89 * hash + Objects.hashCode(this.user);
        hash = 89 * hash + Objects.hashCode(this.foodProductList);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FoodServiceDTO other = (FoodServiceDTO) obj;
        if (this.deliveryFee != other.deliveryFee) {
            return false;
        }
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.foodType, other.foodType)) {
            return false;
        }
        if (!Objects.equals(this.imageUrl, other.imageUrl)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.foodProductList, other.foodProductList)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FoodServiceDTO{" + "id=" + id + ", email=" + email + ", name=" + name + ", address=" + address + ", foodType=" + foodType + ", imageUrl=" + imageUrl + ", deliveryFee=" + deliveryFee + ", active=" + active + ", user=" + user + ", foodProductList=" + foodProductList + '}';
    }

    
}
