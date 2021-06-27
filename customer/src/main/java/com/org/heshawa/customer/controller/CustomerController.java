package com.org.heshawa.customer.controller;

import com.org.heshawa.customer.service.CustomerService;
import com.org.heshawa.customer.dto.request.body.CustomerRequest;
import com.org.heshawa.customer.dto.request.body.LoginRequest;
import com.org.heshawa.customer.dto.response.body.CustomerResponse;
import com.org.heshawa.customer.dto.response.body.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Authenticate user and login user
     *
     * @param request Request body with username and password
     * @return Login status
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateCustomer(@RequestBody LoginRequest request){

        return customerService.authenticateCustomer(request);
    }

    /**
     * Get customer by customer ID
     * @param customerId ID to find customer
     * @return Customer with address and contact number
     */
    @RequestMapping("/{customerId}")
    public CustomerResponse getCustomerById(@PathVariable String customerId){

        return customerService.getCustomerById(customerId);
    }

    /**
     * Retrieve customer with given username
     * @param username Username to find customer
     * @return Customer details for given username
     */
    @RequestMapping("/username/{username}")
    public CustomerResponse getCustomerByUsername(@PathVariable String username){

        return customerService.getCustomerByUsername(username);
    }

    /**
     * Create new customer
     * @param request Request body with registration details
     * @return Created customer with saved data
     */
    @RequestMapping("/register")
    public CustomerResponse addNewCustomer(@RequestBody CustomerRequest request){

        return customerService.addNewCustomer(request);
    }

}
