package com.org.heshawa.payment.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Payment {

    @Id
    @Column(name = "paymentId", nullable = false, length = 20)
    private String paymentId;

    @Basic
    @Column(name = "paymentDate", nullable = false)
    private Date paymentDate;

    @Basic
    @Column(name = "paymentAmount", nullable = false, precision = 2)
    private BigDecimal paymentAmount;

    @Basic
    @Column(name = "details")
    private String details;

    @Basic
    @Column(name = "paymentType", nullable = false)
    private Integer paymentType;

    @Basic
    @Column(name = "customerId", nullable = false, length = 20)
    private String customerId;

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId", nullable = false)
    private Order orderByOrderId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(paymentId, payment.paymentId) &&
                Objects.equals(paymentDate, payment.paymentDate) &&
                Objects.equals(paymentAmount, payment.paymentAmount) &&
                Objects.equals(details, payment.details) &&
                Objects.equals(paymentType, payment.paymentType) &&
                Objects.equals(customerId, payment.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, paymentDate, paymentAmount, details, paymentType, customerId);
    }

}
