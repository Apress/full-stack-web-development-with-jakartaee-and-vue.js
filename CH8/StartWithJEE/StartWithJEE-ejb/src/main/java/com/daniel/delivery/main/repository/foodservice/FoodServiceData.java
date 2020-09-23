/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.delivery.main.repository.foodservice;

import com.daniel.delivery.main.repository.foodproduct.FoodProductData;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "FOOD_SERVICE")
@NamedQueries({
    @NamedQuery(name = "FoodServiceData.findAll", query = "SELECT f FROM FoodServiceData f"),
    @NamedQuery(name = "FoodServiceData.findByEmail", query = "SELECT f FROM FoodServiceData f WHERE f.email = :email"),
    @NamedQuery(name = "FoodServiceData.findByEmailAndPassword", query = "SELECT f FROM FoodServiceData f WHERE f.email = :email and f.userData.email = :email and f.userData.password = :password"),
    @NamedQuery(name = "FoodServiceData.findByName", query = "SELECT f FROM FoodServiceData f WHERE f.name = :name"),
    @NamedQuery(name = "FoodServiceData.findByAddress", query = "SELECT f FROM FoodServiceData f WHERE f.address = :address"),
    @NamedQuery(name = "FoodServiceData.findByFoodType", query = "SELECT f FROM FoodServiceData f WHERE f.foodType = :foodType"),
    @NamedQuery(name = "FoodServiceData.findByDeliveryFee", query = "SELECT f FROM FoodServiceData f WHERE f.deliveryFee = :deliveryFee"),
    @NamedQuery(name = "FoodServiceData.findByActive", query = "SELECT f FROM FoodServiceData f WHERE f.active = :active")})
public class FoodServiceData implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "image_url")
    private String imageUrl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "food_type")
    private String foodType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "delivery_fee")
    private int deliveryFee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foodService")
    private List<FoodProductData> foodProductList;
    @JoinColumn(name = "email", referencedColumnName = "email", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private UserData userData;

    public FoodServiceData() {
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

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<FoodProductData> getFoodProductList() {
        return foodProductList;
    }

    public void setFoodProductList(List<FoodProductData> foodProductList) {
        this.foodProductList = foodProductList;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FoodServiceData)) {
            return false;
        }
        FoodServiceData other = (FoodServiceData) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.packt.delivery.main.data.structure.FoodService[ email=" + email + " ]";
    }
    
}
