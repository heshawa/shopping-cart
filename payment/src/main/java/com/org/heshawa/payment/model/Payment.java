package com.org.heshawa.payment.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Payment {
    private String paymentId;
    private Date paymentDate;
    private BigDecimal paymentAmount;
    private String details;
    private Integer paymentType;
    private String customerId;
    private Order orderByOrderId;

    @Id
    @Column(name = "paymentId", nullable = false, length = 20)
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @Basic
    @Column(name = "paymentDate", nullable = false)
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Basic
    @Column(name = "paymentAmount", nullable = false, precision = 2)
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Basic
    @Column(name = "details", nullable = true, length = 255)
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Basic
    @Column(name = "paymentType", nullable = false)
    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    @Basic
    @Column(name = "customerId", nullable = false, length = 20)
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

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

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId", nullable = false)
    public Order getOrderByOrderId() {
        return orderByOrderId;
    }

    public void setOrderByOrderId(Order orderByOrderId) {
        this.orderByOrderId = orderByOrderId;
    }
}
