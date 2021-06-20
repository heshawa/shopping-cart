package com.org.heshawa.customer.dto;

import com.org.heshawa.customer.enums.exception.CustomerError;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiErrorResponse {
    private long timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ApiErrorResponse(long timestamp, int status, CustomerError error, String errorMessage, String requestURI) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error.getErrorCode();
        this.message = errorMessage;
        this.path = requestURI;
    }
}
