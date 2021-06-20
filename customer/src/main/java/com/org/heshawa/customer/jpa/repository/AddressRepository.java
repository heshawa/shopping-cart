package com.org.heshawa.customer.jpa.repository;

import com.org.heshawa.customer.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address,String> {
}
