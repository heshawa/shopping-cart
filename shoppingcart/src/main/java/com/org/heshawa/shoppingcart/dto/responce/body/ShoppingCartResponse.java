package com.org.heshawa.shoppingcart.dto.responce.body;

import com.org.heshawa.shoppingcart.model.OrderLine;
import com.org.heshawa.shoppingcart.model.ShoppingCart;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ShoppingCartResponse {
    private String shoppingCartId;
    private String customerId;
    private Boolean isPrimary;
    private Integer numberOfItems;
    private BigDecimal cartValue;

    private List<OrderLineResponse> orderLines;

    public void createResponseFromModel(ShoppingCart cart){
        this.shoppingCartId = cart.getShoppingCartId();
        this.customerId = cart.getCustomerId();
        this.isPrimary = cart.getIsPrimary();
        this.numberOfItems = cart.getNumberOfItems();
        this.cartValue = cart.getCartValue();
        this.orderLines = new ArrayList<>();
    }

    public void addOrderLineResponseItem(OrderLine orderLine){
        OrderLineResponse item = new OrderLineResponse();
        item.createResponseFromModel(orderLine);
        item.setDiscount(orderLine.getDiscount());
        item.setPrice(orderLine.getPrice());
        item.setProduct(orderLine.getProductId().getName());
        item.setQuantity(orderLine.getQuantity());
        this.orderLines.add(item);
    }

}
