package com.org.heshawa.shoppingcart.dto.responce.body;

import com.org.heshawa.shoppingcart.model.OrderLine;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderLineResponse {
    private String product;
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal discount;

    public void createResponseFromModel(OrderLine orderLine){
        this.discount = orderLine.getDiscount();
        this.price = orderLine.getPrice();
        this.product = orderLine.getProductId().getName();
        this.quantity = orderLine.getQuantity();
    }

}
