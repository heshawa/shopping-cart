package com.org.heshawa.shoppingcart.model;

import com.org.heshawa.shoppingcart.exception.ShoppingCartException;
import com.org.heshawa.shoppingcart.enums.exception.ShoppingCartError;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "orderline")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderLine {
    @Id
    @Column(name = "orderLineId")
    private String orderLineId;

    @Basic
    @Column(name = "price")
    private BigDecimal price;

    @Basic
    @Column(name = "quantity")
    private BigDecimal quantity;

    @Basic
    @Column(name = "discount")
    private BigDecimal discount;

    @ManyToOne
    @JoinColumn(name = "shoppingCartId", referencedColumnName = "shoppingCartId", nullable = false)
    private ShoppingCart shoppingCartId;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", nullable = false)
    private Product productId;

    public void setQuantity(BigDecimal quantity) {
        if (!productId.getIsUnit()){
            this.quantity = quantity;
        }else if(quantity.stripTrailingZeros().scale() <= 0){
            this.quantity = quantity;
        }else {
            throw new ShoppingCartException(ShoppingCartError.ORDER_LINE_INVALID_QUANTITY);
        }
        calculatePrice();
    }


    /**
     * Calculate and set discount amount for product
     */
    private void getDiscountValue(){
        //TODO Calculate discount from product promotion
        //Returning 0 for now
        this.discount = new BigDecimal(0);
    }

    private void calculatePrice(){
        getDiscountValue();
        this.price = productId.getUnitPrice().multiply(quantity).subtract(discount);
    }

}
