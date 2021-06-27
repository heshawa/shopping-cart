package com.org.heshawa.customer.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactNumber {
    @Id
    @Column(name = "phoneId", nullable = false, length = 20)
    private String phoneId;

    @Basic
    @Column(name = "countryCode", nullable = false)
    private Integer countryCode;

    @Basic
    @Column(name = "phoneNumber", nullable = false)
    private Long phoneNumber;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "customerId", nullable = false)
    private Customer customerByCustomerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactNumber that = (ContactNumber) o;
        return Objects.equals(phoneId, that.phoneId) &&
                Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneId, countryCode, phoneNumber);
    }
}
