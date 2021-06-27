package com.org.heshawa.payment.dto.response.body.cart;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ShoppingCartOrderLine {
    private String orderLineId;
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal discount;
    private ShoppingCart shoppingCartId;
    private ShoppingCartProduct productId;

}
