package com.org.heshawa.shoppingcart.exception;

import com.org.heshawa.shoppingcart.enums.exception.ShoppingCartError;
import lombok.Getter;

@Getter
public class ShoppingCartException extends RuntimeException {
    private final ShoppingCartError errorCode;
    private final String errorMessage;

    public ShoppingCartException(ShoppingCartError errorCode) {
        super(errorCode.getErrorDescription());
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getErrorDescription();
    }
}
