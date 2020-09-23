
package com.daniel.delivery.main.restful.delivery;

import com.daniel.delivery.abstraction.entity.Delivery;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeliveryDTO {
    private Integer id;
    private String address;
    private String phone;
    private int total;
    private int fee;
    private String email;
    private String state;
    private List<ItemDTO> itemList;
    
    public DeliveryDTO(){
        
    }

    public DeliveryDTO(Integer id, String address, String phone, int total, int fee, String email, String state, List<ItemDTO> itemList) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.total = total;
        this.fee = fee;
        this.email = email;
        this.state = state;
        this.itemList = itemList;
    }
    
    public DeliveryDTO(Delivery delivery){
        this.id = delivery.getId();
        this.address = delivery.getAddress();
        this.phone = delivery.getPhone();
        this.total = delivery.getTotal();
        this.fee = delivery.getFee();
        this.email = delivery.getEmail();
        this.state = delivery.getState();
        this.itemList = Optional.ofNullable(delivery.getItemList())
                .orElse(Collections.emptyList())
                .stream()
                .map(i -> new ItemDTO(i))
                .collect(Collectors.toList());
    }
    
    public Delivery toDelivery(){
        return new Delivery(this.id, this.address, this.phone, this.total, this.fee, this.email, this.state, Optional.ofNullable(this.itemList)
                .orElse(Collections.emptyList())
                .stream()
                .map(ItemDTO::toItem)
                .collect(Collectors.toList()));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ItemDTO> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemDTO> itemList) {
        this.itemList = itemList;
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.address);
        hash = 59 * hash + Objects.hashCode(this.phone);
        hash = 59 * hash + this.total;
        hash = 59 * hash + this.fee;
        hash = 59 * hash + Objects.hashCode(this.email);
        hash = 59 * hash + Objects.hashCode(this.state);
        hash = 59 * hash + Objects.hashCode(this.itemList);
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
        final DeliveryDTO other = (DeliveryDTO) obj;
        if (this.total != other.total) {
            return false;
        }
        if (this.fee != other.fee) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.itemList, other.itemList)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DeliveryDTO{" + "id=" + id + ", address=" + address + ", phone=" + phone + ", total=" + total + ", fee=" + fee + ", email=" + email + ", state=" + state + ", itemList=" + itemList + '}';
    }

}
