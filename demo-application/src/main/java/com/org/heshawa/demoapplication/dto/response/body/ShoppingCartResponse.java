package com.org.heshawa.demoapplication.dto.response.body;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
public class ShoppingCartResponse {
    private String shoppingCartId;
    private String customerId;
    private Boolean isPrimary;
    private Integer numberOfItems;
    private BigDecimal cartValue;

    private List<OrderLineResponse> orderLines;

}
