package com.org.heshawa.shoppingcart;

import com.org.heshawa.shoppingcart.dto.ApiErrorResponse;
import com.org.heshawa.shoppingcart.exception.ShoppingCartException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice

public class ResponseExceptionHandler {
    @ExceptionHandler(ShoppingCartException.class)
    public final ResponseEntity<ApiErrorResponse> handleBusinessException(HttpServletRequest request, ShoppingCartException e){
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
