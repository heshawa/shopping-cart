package com.org.heshawa.payment.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum OrderStatus {
    NEW(1,"Newly created order"),
    ITEMS_UPDATED_FROM_CART(2,"Order lines from cart inserted the order"),
    ORDER_PAYMENT_RECEIVED(3,"Order payment completed"),
    ORDER_SHIPPED(4,"Order handed over to courier"),
    ORDER_DELIVERED(5,"Order delivered successfully"),
    ORDER_CLOSED(6,"Order closed"),

    ;
    private final Integer orderStatus;
    private final String statusDescription;


    OrderStatus(Integer orderStatus, String statusDescription) {
        this.orderStatus = orderStatus;
        this.statusDescription = statusDescription;
    }
}
