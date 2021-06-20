package com.org.heshawa.customer.enums.exception;

import lombok.Getter;

@Getter
public enum CustomerError {
    CUSTOMER_NOT_FOUND("CUSTOMER-001", "Unable to find customer"),
    CUSTOMER_CREATION_FAILED("CUSTOMER-002", "Unable to create customer"),
    CUSTOMER_USERNAME_NOT_FOUND("CUSTOMER-003", "Customer username not found")
    ;

    private final String errorCode;
    private final String errorDescription;

    CustomerError(String errorCode, String errorDescription){
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

}
