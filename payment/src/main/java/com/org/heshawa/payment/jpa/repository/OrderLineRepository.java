package com.org.heshawa.payment.jpa.repository;

import com.org.heshawa.payment.model.Order;
import com.org.heshawa.payment.model.OrderLine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface OrderLineRepository extends CrudRepository<OrderLine,String> {
    @Transactional
    void deleteAllByOrderId(Order order);
}
