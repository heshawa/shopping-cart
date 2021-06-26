package com.org.heshawa.payment.jpa.repository;

import com.org.heshawa.payment.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order,String> {
    Optional<Order> findByCustomerIdAndOrderStatusLessThan(String customerId, Integer oderStatus);
}
