package com.org.heshawa.customer.service;

import com.org.heshawa.customer.dto.request.body.CustomerRequest;
import com.org.heshawa.customer.dto.request.body.LoginRequest;
import com.org.heshawa.customer.dto.response.body.CustomerResponse;
import com.org.heshawa.customer.dto.response.body.LoginResponse;
import com.org.heshawa.customer.enums.LoginStatus;
import com.org.heshawa.customer.enums.exception.CustomerError;
import com.org.heshawa.customer.exception.CustomerException;
import com.org.heshawa.customer.jpa.repository.AddressRepository;
import com.org.heshawa.customer.jpa.repository.ContactNUmberRepository;
import com.org.heshawa.customer.jpa.repository.CustomerRepository;
import com.org.heshawa.customer.model.Address;
import com.org.heshawa.customer.model.ContactNumber;
import com.org.heshawa.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ContactNUmberRepository contactNUmberRepository;

    @Override
    public ResponseEntity<LoginResponse> authenticateCustomer(LoginRequest request){
        Customer customer = customerRepository.findByUsername(request.getUsername());
        if (customer == null || !customer.getPassword().equals(request.getPassword())){
            return ResponseEntity.status(403).body(new LoginResponse(LoginStatus.LOGIN_FAILED));
        }
        //TODO Add customer status column in customer table
        //TODO Encrypt password and compare hashed string
        //TODO Implement authorization mechanism
        //TODO Implement for other user statuses

        return ResponseEntity.ok().body(new LoginResponse(LoginStatus.LOGIN_SUCCESSFUL));
    }

    @Override
    public CustomerResponse getCustomerById(String customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null){
            throw new CustomerException(CustomerError.CUSTOMER_NOT_FOUND);
        }

        return createCustomerResponse(customer);
    }

    @Override
    public CustomerResponse getCustomerByUsername(String username) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null){
            throw new CustomerException(CustomerError.CUSTOMER_USERNAME_NOT_FOUND);
        }

        return createCustomerResponse(customer);
    }

    @Override
    public CustomerResponse addNewCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .customerId(getRandomID(5))
                .email(request.getEmail())
                .name(request.getName())
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
        //TODO Use encryption algorithm to convert password
        customer.setAddressId(customer.getCustomerId().concat("-").concat(getRandomID(5)));
        customer.setContactNumber(customer.getCustomerId().concat("-").concat(getRandomID(5)));

        Address customerAddress = Address.builder()
                .addressId(customer.getAddressId())
                .houseNumber(request.getHouseNumber())
                .addressLine1(request.getAddressLine1())
                .addressLine2(request.getAddressLine2())
                .city(request.getCity())
                .customerByCustomerId(customer)
                .build();

        ContactNumber contactNumber =ContactNumber.builder()
                .phoneId(customer.getContactNumber())
                .customerByCustomerId(customer)
                .countryCode(Integer.parseInt(request.getContactCountryCode()))
                .phoneNumber(Long.parseLong(request.getContactNumber())).build();

        try{
            customerRepository.save(customer);
            addressRepository.save(customerAddress);
            contactNUmberRepository.save(contactNumber);
        }catch (Exception ex){
            //TODO Implement rollback if 1 has performed successfully
            ex.printStackTrace();
            throw new CustomerException(CustomerError.CUSTOMER_CREATION_FAILED);
        }

        customer = customerRepository.findById(customer.getCustomerId()).orElse(null);
        if (customer == null){
            throw new CustomerException(CustomerError.CUSTOMER_CREATION_FAILED);
        }
        return createCustomerResponse(customer);
    }

    /**
     * Omit sensitive data and create response to front end
     * @param customer Customer entity from database
     * @return Customer details
     */
    private CustomerResponse createCustomerResponse(Customer customer){
        Address customerAddress = addressRepository.findById(customer.getAddressId()).orElse(null);
        ContactNumber customerContactNumber = contactNUmberRepository.findById(customer.getContactNumber())
                .orElse(null);

        CustomerResponse response = new CustomerResponse();
        response.createResponseFromModel(customer);
        response.getContactNumber().createResponseFromModel(customerContactNumber);
        response.getAddress().createResponseFromModel(customerAddress);

        return response;

    }

    /**
     * Generate and return numeric string
     * @param numberOfCharacters Number of characters requires
     * @return String with required length
     */
    private String getRandomID(int numberOfCharacters){
        return String.valueOf(Math.round(Math.random()*100000));
    }
}
