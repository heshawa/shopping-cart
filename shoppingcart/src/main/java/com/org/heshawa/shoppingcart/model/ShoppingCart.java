package com.org.heshawa.shoppingcart.model;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "shoppingcart")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ShoppingCart {
    @Id
    @Column(name = "shoppingCartId", nullable = false, length = 20)
    private String shoppingCartId;

    @Basic
    @Column(name = "customerId", nullable = false, length = 20)
    private String customerId;

    @Basic
    @Column(name = "isPrimary", nullable = false)
    private Boolean isPrimary;

    @Basic
    @Column(name = "numberOfItems", nullable = false)
    private Integer numberOfItems;

    @Basic
    @Column(name = "cartValue", nullable = false, precision = 2)
    private BigDecimal cartValue;

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
