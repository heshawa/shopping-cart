package com.org.heshawa.shoppingcart.dto.responce.body;

import com.org.heshawa.shoppingcart.model.customer.ContactNumber;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactNumberResponse {
    private String countryCode;
    private String phoneNumber;

    public void createResponseFromModel(ContactNumber contactNumber){
        this.countryCode = contactNumber.getCountryCode().toString();
        this.phoneNumber = contactNumber.getPhoneNumber().toString();
    }
}
