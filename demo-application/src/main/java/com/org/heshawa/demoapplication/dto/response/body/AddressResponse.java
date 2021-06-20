package com.org.heshawa.demoapplication.dto.response.body;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {
    private String houseNumber;
    private String addressLine1;
    private String addressLine2;
    private String city;
}
