package com.org.heshawa.customer.service;

import com.org.heshawa.customer.dto.request.body.CustomerRequest;
import com.org.heshawa.customer.dto.request.body.LoginRequest;
import com.org.heshawa.customer.dto.response.body.CustomerResponse;
import com.org.heshawa.customer.dto.response.body.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    ResponseEntity<LoginResponse> authenticateCustomer(LoginRequest request);
    CustomerResponse getCustomerById(String customerId);
    CustomerResponse getCustomerByUsername(String username);
    CustomerResponse addNewCustomer(CustomerRequest request);
}
