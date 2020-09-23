/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.delivery.main.repository.delivery;

import com.daniel.delivery.main.repository.foodproduct.FoodProductData;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "ITEM")
@NamedQueries({
    @NamedQuery(name = "ItemData.findAll", query = "SELECT i FROM ItemData i"),
    @NamedQuery(name = "ItemData.findById", query = "SELECT i FROM ItemData i WHERE i.id = :id"),
    @NamedQuery(name = "ItemData.findByAmount", query = "SELECT i FROM ItemData i WHERE i.amount = :amount")})
public class ItemData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private int amount;
    @JoinColumn(name = "delivery", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DeliveryData delivery;
    @JoinColumn(name = "food_product", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FoodProductData foodProduct;

    public ItemData() {
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

    public DeliveryData getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryData delivery) {
        this.delivery = delivery;
    }

    public FoodProductData getFoodProduct() {
        return foodProduct;
    }

    public void setFoodProduct(FoodProductData foodProduct) {
        this.foodProduct = foodProduct;
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
        if (!(object instanceof ItemData)) {
            return false;
        }
        ItemData other = (ItemData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.packt.delivery.main.data.structure.Item[ id=" + id + " ]";
    }
    
}
