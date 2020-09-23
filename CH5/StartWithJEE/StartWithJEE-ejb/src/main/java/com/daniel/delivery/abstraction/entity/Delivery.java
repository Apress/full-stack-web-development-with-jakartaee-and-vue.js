package com.daniel.delivery.abstraction.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Delivery implements Serializable {
    private final Integer id;
    private final String address;
    private final String phone;
    private final int total;
    private final int fee;
    private final String email;
    private final String state;
    private final List<Item> itemList;

    public Delivery(Integer id, String address, String phone, int total, int fee, String email, String state, List<Item> itemList) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.total = total;
        this.fee = fee;
        this.email = email;
        this.state = state;
        this.itemList = itemList;
    }

    public Integer getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public int getTotal() {
        return total;
    }

    public int getFee() {
        return fee;
    }

    public String getEmail() {
        return email;
    }

    public String getState() {
        return state;
    }

    public List<Item> getItemList() {
        return itemList;
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
        final Delivery other = (Delivery) obj;
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
        return "Delivery{" + "id=" + id + ", address=" + address + ", phone=" + phone + ", total=" + total + ", fee=" + fee + ", email=" + email + ", state=" + state + ", itemList=" + itemList + '}';
    }

}
