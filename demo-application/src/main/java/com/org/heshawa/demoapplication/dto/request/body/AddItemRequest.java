package com.org.heshawa.demoapplication.dto.request.body;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddItemRequest {
    private String productId;
    private Double quantity;
}
