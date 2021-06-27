package com.org.heshawa.payment.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "orderline")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderLine {

    @Id
    @Column(name = "orderLineId", nullable = false, length = 20)
    private String orderLineId;

    @Basic
    @Column(name = "price", nullable = false, precision = 2)
    private BigDecimal price;

    @Basic
    @Column(name = "discount", precision = 2)
    private BigDecimal discount;

    @Basic
    @Column(name = "quantity", nullable = false, precision = 3)
    private BigDecimal quantity;

    @Basic
    @Column(name = "productDescription", nullable = false)
    private String productDescription;

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId", nullable = false)
    private Order orderId;

}
