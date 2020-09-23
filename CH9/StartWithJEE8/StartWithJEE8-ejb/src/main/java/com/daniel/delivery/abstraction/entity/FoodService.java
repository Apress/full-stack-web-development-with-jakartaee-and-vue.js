package com.daniel.delivery.abstraction.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class FoodService implements Serializable {

    private final String id;
    private final String email;
    private final String name;
    private final String address;
    private final String imageUrl;
    private final String foodType;
    private final int deliveryFee;
    private boolean active;
    private final User user;
    private final List<FoodProduct> foodProductList;

    public FoodService(String id, String email, String name, String address, String imageUrl, String foodType, int deliveryFee, boolean active, User user, List<FoodProduct> foodProductList) {
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

    public String getId() {
        return id;
    }

    public void deActivate(){
        this.active = false;
    }
    
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getFoodType() {
        return foodType;
    }

    public int getDeliveryFee() {
        return deliveryFee;
    }

    public boolean getActive() {
        return active;
    }

    public User getUser() {
        return user;
    }

    public List<FoodProduct> getFoodProductList() {
        return foodProductList;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.email);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.address);
        hash = 59 * hash + Objects.hashCode(this.imageUrl);
        hash = 59 * hash + Objects.hashCode(this.foodType);
        hash = 59 * hash + this.deliveryFee;
        hash = 59 * hash + (this.active ? 1 : 0);
        hash = 59 * hash + Objects.hashCode(this.user);
        hash = 59 * hash + Objects.hashCode(this.foodProductList);
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
        final FoodService other = (FoodService) obj;
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
        if (!Objects.equals(this.imageUrl, other.imageUrl)) {
            return false;
        }
        if (!Objects.equals(this.foodType, other.foodType)) {
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
        return "FoodService{" + "id=" + id + ", email=" + email + ", name=" + name + ", address=" + address + ", imageUrl=" + imageUrl + ", foodType=" + foodType + ", deliveryFee=" + deliveryFee + ", active=" + active + ", user=" + user + ", foodProductList=" + foodProductList + '}';
    }  
    
}
