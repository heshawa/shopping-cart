package com.org.heshawa.customer.enums;

import lombok.Getter;

@Getter
public enum LoginStatus {
    LOGIN_SUCCESSFUL(true,"Login successful"),
    LOGIN_FAILED(false,"Incorrect username or password"),
    BANNED_USER(false,"User has been blocked")
    ;

    private final Boolean loginSuccess;
    private final String statusDescription;

    LoginStatus(Boolean loginStatus, String statusDescription) {
        this.loginSuccess = loginStatus;
        this.statusDescription = statusDescription;
    }
}
