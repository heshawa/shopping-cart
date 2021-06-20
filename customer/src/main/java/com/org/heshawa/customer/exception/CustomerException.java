package com.org.heshawa.customer.exception;

import com.org.heshawa.customer.enums.exception.CustomerError;
import lombok.Getter;

@Getter
public class CustomerException extends RuntimeException {
    private final CustomerError errorCode;
    private final String errorMessage;

    public CustomerException(CustomerError errorCode) {
        super(errorCode.getErrorDescription());
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getErrorDescription();
    }
}
