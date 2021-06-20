package com.org.heshawa.shoppingcart.model;

import com.org.heshawa.shoppingcart.exception.ShoppingCartException;
import com.org.heshawa.shoppingcart.enums.exception.ShoppingCartError;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "orderline")
public class OrderLine {
    private String orderLineId;
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal discount;
    private ShoppingCart shoppingCartId;
    private Product productId;

    @Id
    @Column(name = "orderLineId")
    public String getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(String orderLineId) {
        this.orderLineId = orderLineId;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "quantity")
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        if (!productId.getIsUnit()){
            this.quantity = quantity;
        }else if(productId.getIsUnit() && quantity.stripTrailingZeros().scale() <= 0){
            this.quantity = quantity;
        }else {
            throw new ShoppingCartException(ShoppingCartError.ORDER_LINE_INVALID_QUANTITY);
        }
    }

    @Basic
    @Column(name = "discount")
    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @ManyToOne
    @JoinColumn(name = "shoppingCartId", referencedColumnName = "shoppingCartId", nullable = false)
    public ShoppingCart getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(ShoppingCart shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", nullable = false)
    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    /**
     * Calculate and set discount amount for product
     */
    private void getDiscountValue(){
        //TODO Calculate discount from product promotion
        //Returning 0 for now
        this.discount = new BigDecimal(0);
    }

    public void calculatePrice(){
        getDiscountValue();
        this.price = productId.getUnitPrice().multiply(quantity).subtract(discount);
    }

}
