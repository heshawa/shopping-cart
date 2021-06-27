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
public class Address {
    @Id
    @Column(name = "addressId", nullable = false, length = 20)
    private String addressId;

    @Basic
    @Column(name = "houseNumber", nullable = false, length = 45)
    private String houseNumber;

    @Basic
    @Column(name = "addressLine1", nullable = false, length = 45)
    private String addressLine1;

    @Basic
    @Column(name = "addressLine2", length = 45)
    private String addressLine2;

    @Basic
    @Column(name = "city", nullable = false, length = 45)
    private String city;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "customerId", nullable = false)
    private Customer customerByCustomerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(addressId, address.addressId) &&
                Objects.equals(houseNumber, address.houseNumber) &&
                Objects.equals(addressLine1, address.addressLine1) &&
                Objects.equals(addressLine2, address.addressLine2) &&
                Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, houseNumber, addressLine1, addressLine2, city);
    }

}
