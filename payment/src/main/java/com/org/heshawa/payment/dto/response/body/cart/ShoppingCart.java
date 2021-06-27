package com.org.heshawa.payment.dto.response.body.cart;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ShoppingCart {
    private String shoppingCartId;
    private String customerId;
    private Boolean isPrimary;
    private Integer numberOfItems;
    private BigDecimal cartValue;

}
