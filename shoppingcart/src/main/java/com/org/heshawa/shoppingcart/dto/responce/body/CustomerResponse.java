package com.org.heshawa.shoppingcart.dto.responce.body;

import com.org.heshawa.shoppingcart.model.customer.Customer;
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

    public void createResponseFromModel(Customer customer){
        this.name = customer.getName();
        this.email= customer.getEmail();
        this.username = customer.getUsername();
        this.customerID = customer.getCustomerId();
        this.contactNumber = new ContactNumberResponse();
        this.address = new AddressResponse();
    }

}
