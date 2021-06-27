package com.org.heshawa.customer.model;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    @Column(name = "customerId", nullable = false, length = 20)
    private String customerId;

    @Basic
    @Column(name = "Name", nullable = false)
    private String name;

    @Basic
    @Column(name = "addressId", nullable = false, length = 20)
    private String addressId;

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Basic
    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    @Basic
    @Column(name = "contactNumber", nullable = false, length = 20)
    private String contactNumber;

}
