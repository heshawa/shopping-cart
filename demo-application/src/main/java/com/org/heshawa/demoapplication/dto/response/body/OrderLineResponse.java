package com.org.heshawa.demoapplication.dto.response.body;

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

}
