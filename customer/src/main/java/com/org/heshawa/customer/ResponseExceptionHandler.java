package com.org.heshawa.customer;

import com.org.heshawa.customer.dto.ApiErrorResponse;
import com.org.heshawa.customer.exception.CustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice

public class ResponseExceptionHandler {
    @ExceptionHandler(CustomerException.class)
    public final ResponseEntity<ApiErrorResponse> handleBusinessException(HttpServletRequest request, CustomerException e){
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
