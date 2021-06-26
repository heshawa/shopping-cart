package com.org.heshawa.payment;

import com.org.heshawa.payment.dto.ApiErrorResponse;
import com.org.heshawa.payment.exception.PaymentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice

public class ResponseExceptionHandler {
    @ExceptionHandler(PaymentException.class)
    public final ResponseEntity<ApiErrorResponse> handleBusinessException(HttpServletRequest request, PaymentException e){
        ApiErrorResponse rs = new ApiErrorResponse(
                new Date().getTime(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                e.getErrorCode(),
                e.getErrorMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(rs, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
