package com.org.heshawa.payment.jpa.repository;

import com.org.heshawa.payment.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,String> {
}
