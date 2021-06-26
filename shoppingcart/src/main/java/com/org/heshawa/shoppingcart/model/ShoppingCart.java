package com.org.heshawa.shoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "shoppingcart")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    /**
     * Update cart attributes with changing order lines
     * @param existingOrderLine Current item line in the cart
     * @param newOrderLine Updated/new/deleted oder line in cart
     */
    public void updateCart(OrderLine existingOrderLine, OrderLine newOrderLine){
        if (existingOrderLine == null){
            this.numberOfItems += 1;
            this.cartValue = this.cartValue.add(newOrderLine.getPrice());
        } else if(newOrderLine == null){
            this.numberOfItems -= 1;
            this.cartValue = this.cartValue.subtract(existingOrderLine.getPrice());
        }else{
            this.cartValue = this.cartValue.subtract(existingOrderLine.getPrice());
            this.cartValue = this.cartValue.add(newOrderLine.getPrice());
        }
    }

}
