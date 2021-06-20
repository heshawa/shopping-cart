package com.org.heshawa.shoppingcart.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "shoppingcart")
public class ShoppingCart {
    private String shoppingCartId;
    private String customerId;
    private Boolean isPrimary;
    private Integer numberOfItems;
    private BigDecimal cartValue;

    @Id
    @Column(name = "shoppingCartId", nullable = false, length = 20)
    public String getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(String shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    @Basic
    @Column(name = "customerId", nullable = false, length = 20)
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "isPrimary", nullable = false)
    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    @Basic
    @Column(name = "numberOfItems", nullable = false)
    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    @Basic
    @Column(name = "cartValue", nullable = false, precision = 2)
    public BigDecimal getCartValue() {
        return cartValue;
    }

    public void setCartValue(BigDecimal cartValue) {
        this.cartValue = cartValue;
    }

    public void increaseCartItemCount() {
        this.numberOfItems += 1;
    }

    public void increaseCartValue(BigDecimal price) {
        this.cartValue = this.cartValue.add(price);
    }

    public void decreaseCartItemCount() {
        this.numberOfItems -= 1;
    }

    public void decreaseCartValue(BigDecimal price) {
        this.cartValue = this.cartValue.subtract(price);
    }
}
