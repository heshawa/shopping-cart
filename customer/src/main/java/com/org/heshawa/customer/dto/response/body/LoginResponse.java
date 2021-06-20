package com.org.heshawa.customer.dto.response.body;

import com.org.heshawa.customer.enums.LoginStatus;
import lombok.Getter;

@Getter
public class LoginResponse {
    private final Boolean loginSuccess;
    private final String message;

    public LoginResponse(LoginStatus status) {
        this.loginSuccess = status.getLoginSuccess();
        this.message = status.getStatusDescription();
    }
}
