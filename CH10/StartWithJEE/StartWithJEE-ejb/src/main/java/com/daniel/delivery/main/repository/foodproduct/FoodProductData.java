/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.delivery.main.repository.foodproduct;

import com.daniel.delivery.main.repository.delivery.ItemData;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "FOOD_PRODUCT")
@NamedQueries({
    @NamedQuery(name = "FoodProductData.findAll", query = "SELECT f FROM FoodProductData f"),
    @NamedQuery(name = "FoodProductData.findById", query = "SELECT f FROM FoodProductData f WHERE f.id = :id"),
    @NamedQuery(name = "FoodProductData.findByName", query = "SELECT f FROM FoodProductData f WHERE f.name = :name"),
    @NamedQuery(name = "FoodProductData.findByPrice", query = "SELECT f FROM FoodProductData f WHERE f.price = :price"),
    @NamedQuery(name = "FoodProductData.findByDescription", query = "SELECT f FROM FoodProductData f WHERE f.description = :description"),
    @NamedQuery(name = "FoodProductData.findByImageUrl", query = "SELECT f FROM FoodProductData f WHERE f.imageUrl = :imageUrl"),
    @NamedQuery(name = "FoodProductData.findByActive", query = "SELECT f FROM FoodProductData f WHERE f.active = :active"),
    @NamedQuery(name = "FoodProductData.findByFoodService", query = "SELECT f FROM FoodProductData f WHERE f.foodService = :foodService")})
public class FoodProductData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "image_url")
    private String imageUrl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "food_service")
    private String foodService;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foodProduct")
    private List<ItemData> itemList;

    public FoodProductData() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<ItemData> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemData> itemList) {
        this.itemList = itemList;
    }

    public String getFoodService() {
        return foodService;
    }

    public void setFoodService(String foodService) {
        this.foodService = foodService;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FoodProductData)) {
            return false;
        }
        FoodProductData other = (FoodProductData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.packt.delivery.main.data.structure.FoodProduct[ id=" + id + " ]";
    }
}
