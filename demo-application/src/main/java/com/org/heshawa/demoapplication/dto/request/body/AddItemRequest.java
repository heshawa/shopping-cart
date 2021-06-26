package com.org.heshawa.demoapplication.dto.request.body;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddItemRequest {
    private String productId;
    private Double quantity;
}
