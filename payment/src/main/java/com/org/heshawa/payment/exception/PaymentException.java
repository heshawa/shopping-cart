package com.org.heshawa.payment.exception;

import com.org.heshawa.payment.enums.PaymentError;
import lombok.Getter;

@Getter
public class PaymentException extends RuntimeException {
    private final PaymentError errorCode;
    private final String errorMessage;

    public PaymentException(PaymentError errorCode) {
        super(errorCode.getErrorDescription());
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getErrorDescription();
    }
}
