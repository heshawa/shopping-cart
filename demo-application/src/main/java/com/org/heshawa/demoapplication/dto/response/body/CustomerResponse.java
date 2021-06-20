package com.org.heshawa.demoapplication.dto.response.body;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {
    private String name;
    private String email;
    private String username;
    private String customerID;
    private ContactNumberResponse contactNumber;
    private AddressResponse address;

}
