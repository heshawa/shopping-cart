package com.org.heshawa.customer;

import com.org.heshawa.customer.controller.GetCustomer;
import com.org.heshawa.customer.dto.request.body.LoginRequest;
import com.org.heshawa.customer.dto.response.body.CustomerResponse;
import com.org.heshawa.customer.dto.response.body.LoginResponse;
import com.org.heshawa.customer.jpa.repository.AddressRepository;
import com.org.heshawa.customer.jpa.repository.ContactNUmberRepository;
import com.org.heshawa.customer.jpa.repository.CustomerRepository;
import com.org.heshawa.customer.model.Address;
import com.org.heshawa.customer.model.ContactNumber;
import com.org.heshawa.customer.model.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerApplicationTests {

    @Autowired
    private GetCustomer getCustomer;

    @MockBean
    private CustomerRepository customerRepository;

    @Bean
    public CustomerRepository getCustomerRepository(){
        return customerRepository;
    }

    @MockBean
    private AddressRepository addressRepository;

    @Bean
    public AddressRepository getAddressRepository(){
        return addressRepository;
    }

    @MockBean
    private ContactNUmberRepository contactNUmberRepository;

    @Bean
    public ContactNUmberRepository getContactNUmberRepository(){
        return contactNUmberRepository;
    }

    private Customer customer;

    private LoginRequest request;
    private LoginRequest failRequest;
    private LoginRequest invalidUserRequest;
    private CustomerResponse customerResponse;

    @Before
    public void setUp(){
        request = new LoginRequest();
        request.setUsername("hdesilva");
        request.setPassword("test123");

        failRequest = new LoginRequest();
        failRequest.setUsername("heshawa");
        failRequest.setPassword("123");

        invalidUserRequest = new LoginRequest();
        invalidUserRequest.setUsername("harindud");
        invalidUserRequest.setPassword("123");

        Customer loginFailedCustomer = Customer.builder()
                .username(failRequest.getUsername())
                .password(failRequest.getPassword().concat("Abc")).build();

        customer = Customer.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email("ahh.desilva@gmail.com")
                .customerId("Ab123")
                .name("Heshawa De Silva").build();

        Address address = Address.builder()
                .customerByCustomerId(customer)
                .addressId(customer.getCustomerId().concat("-").concat("123AE"))
                .city("Panadura").build();

        ContactNumber contactNumber = ContactNumber.builder()
                .customerByCustomerId(customer)
                .phoneId(customer.getCustomerId().concat("-").concat("98765"))
                .countryCode(94)
                .phoneNumber((long) 772325533).build();

        customer.setAddressId(address.getAddressId());
        customer.setContactNumber(contactNumber.getPhoneId());

        customerResponse = new CustomerResponse();
        customerResponse.createResponseFromModel(customer);
        customerResponse.getAddress().createResponseFromModel(address);
        customerResponse.getContactNumber().createResponseFromModel(contactNumber);


        Mockito.when(customerRepository
                .findByUsername(request.getUsername())
        ).thenReturn(customer);

        Mockito.when(customerRepository
                .findById(customer.getCustomerId())
        ).thenReturn(java.util.Optional.ofNullable(customer));

        Mockito.when(customerRepository
                .findByUsername(failRequest.getUsername())
        ).thenReturn(loginFailedCustomer);

        Mockito.when(customerRepository
                .findByUsername(invalidUserRequest.getUsername())
        ).thenReturn(null);

        Mockito.when(addressRepository
                .findById(customer.getAddressId())
        ).thenReturn(java.util.Optional.of(address));

        Mockito.when(contactNUmberRepository
                .findById(contactNumber.getPhoneId())
        ).thenReturn(java.util.Optional.of(contactNumber));
    }

    @Test
    public void testAuthenticateCustomer() {
        ResponseEntity<LoginResponse> response = getCustomer.authenticateCustomer(request);
        Assert.assertEquals("Login success test failed"
                ,true, Objects.requireNonNull(response.getBody()).getLoginSuccess());
    }

    @Test
    public void testAuthenticateCustomerFailure() {
        ResponseEntity<LoginResponse> response = getCustomer.authenticateCustomer(failRequest);
        Assert.assertEquals("Login failure test failed"
                ,false,response.getBody().getLoginSuccess());
        Assert.assertEquals("Login failure response status is not 403"
                ,403,response.getStatusCodeValue());
    }

    @Test
    public void testAuthenticateCustomerUserNotFound() {
        ResponseEntity<LoginResponse> response = getCustomer.authenticateCustomer(invalidUserRequest);
        Assert.assertEquals("Login failure test failed"
                ,false,response.getBody().getLoginSuccess());
        Assert.assertEquals("Login failure response status is not 403"
                ,403,response.getStatusCodeValue());
    }

    @Test
    public void testGetCustomerById() {
        CustomerResponse response = getCustomer.getCustomerById(customer.getCustomerId());
        Assert.assertEquals("Correct city in customer not found"
                ,customerResponse.getAddress().getCity()
                ,response.getAddress().getCity()
        );
        Assert.assertEquals("Correct phone number not found"
                ,customerResponse.getContactNumber().getPhoneNumber()
                ,response.getContactNumber().getPhoneNumber()
        );
    }

}
