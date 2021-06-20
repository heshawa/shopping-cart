package com.org.heshawa.payment.enums;

import lombok.Getter;

@Getter
public enum PaymentError {
    SHOPPING_CART_RETRIEVAL_FAILED("PAYMENT-001", "Shopping cart retrieval failed"),
    ORDER_LINES_RETRIEVAL_FAILED("PAYMENT-002", "Order lines retrieval failed from shopping cart")
    ;

    private final String errorCode;
    private final String errorDescription;

    PaymentError(String errorCode, String errorDescription){
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

}
