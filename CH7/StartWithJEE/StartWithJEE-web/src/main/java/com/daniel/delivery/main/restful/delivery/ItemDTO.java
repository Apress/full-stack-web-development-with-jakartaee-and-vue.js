package com.daniel.delivery.main.restful.delivery;

import com.daniel.delivery.abstraction.entity.Item;
import com.daniel.delivery.main.restful.foodproduct.FoodProductDTO;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

public class ItemDTO implements Serializable {
    private Integer id;
    private int amount;
    private FoodProductDTO foodProduct;

    public ItemDTO(){
        
    }
    
    public ItemDTO(Integer id, int amount, FoodProductDTO foodProduct) {
        this.id = id;
        this.amount = amount;
        this.foodProduct = foodProduct;
    }
    
    public ItemDTO(Item item){
        this.id = item.getId();
        this.amount = item.getAmount();
        this.foodProduct = Optional.ofNullable(item.getFoodProduct())
                .map(f -> new FoodProductDTO(f))
                .orElse(null);
    }
    
    public Item toItem(){
        return new Item(this.id, this.amount, this.foodProduct.toFoodProduct());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public FoodProductDTO getFoodProduct() {
        return foodProduct;
    }

    public void setFoodProduct(FoodProductDTO foodProduct) {
        this.foodProduct = foodProduct;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + this.amount;
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
        final ItemDTO other = (ItemDTO) obj;
        if (this.amount != other.amount) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItemDTO{" + "id=" + id + ", amount=" + amount + '}';
    }

    
}
