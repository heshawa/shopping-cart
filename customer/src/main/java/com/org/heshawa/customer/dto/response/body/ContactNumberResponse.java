package com.org.heshawa.customer.dto.response.body;

import com.org.heshawa.customer.model.ContactNumber;
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
