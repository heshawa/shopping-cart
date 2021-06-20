package com.org.heshawa.customer.dto.response.body;

import com.org.heshawa.customer.model.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {
    private String houseNumber;
    private String addressLine1;
    private String addressLine2;
    private String city;

    public void createResponseFromModel(Address address){
        this.houseNumber = address.getHouseNumber();
        this.addressLine1 = address.getAddressLine1();
        this.addressLine2 = address.getAddressLine2();
        this.city = address.getCity();
    }

}
