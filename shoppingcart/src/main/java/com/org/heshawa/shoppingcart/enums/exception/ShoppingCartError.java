package com.org.heshawa.shoppingcart.enums.exception;

import lombok.Getter;

@Getter
public enum ShoppingCartError {
    NO_CARTS_FOR_CUSTOMER("SHOPPING-CART-001", "No shopping carts for this customer"),
    CART_NOT_FOUND("SHOPPING-CART-002", "Provided cart not found"),
    PRODUCT_NOT_FOUND("SHOPPING-CART-003", "Provided product not found"),
    ORDER_LINE_INVALID_QUANTITY("SHOPPING-CART-004", "Item quantity cannot have decimal value"),
    CUSTOMER_NOT_FOUND("SHOPPING-CART-005", "Customer with given ID not found")
    ;

    private final String errorCode;
    private final String errorDescription;

    ShoppingCartError(String errorCode, String errorDescription){
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

}
