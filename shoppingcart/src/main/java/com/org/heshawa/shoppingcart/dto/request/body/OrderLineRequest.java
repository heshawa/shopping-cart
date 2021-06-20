package com.org.heshawa.shoppingcart.dto.request.body;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderLineRequest {
    @NotNull
    private BigDecimal quantity;

    @NotBlank
    private String productId;

    private Double discount;

}
