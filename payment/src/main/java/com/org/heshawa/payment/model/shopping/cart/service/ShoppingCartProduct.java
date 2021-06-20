package com.org.heshawa.payment.model.shopping.cart.service;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ShoppingCartProduct {
    private String productId;
    private String name;
    private BigDecimal unitPrice;
    private BigDecimal available;
    private BigDecimal sold;
    private BigDecimal reserved;
    private Boolean isUnit;
    private BigDecimal tax;

}
