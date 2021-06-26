package com.org.heshawa.demoapplication.dto.request.body;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerRequest {
    private String name;
    private String email;
    private String username;
    private String password;
    private String houseNumber;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String contactCountryCode;
    private String contactNumber;

}
