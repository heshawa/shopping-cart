package com.org.heshawa.customer.jpa.repository;

import com.org.heshawa.customer.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,String> {
    Customer findByUsername(String username);
}
