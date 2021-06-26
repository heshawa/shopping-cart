package com.org.heshawa.customer.dto.request.body;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @NotBlank
    private String houseNumber;
    @NotBlank
    private String addressLine1;
    private String addressLine2;
    @NotBlank
    private String city;

    @NotBlank
    @Size(min = 2,max = 4)
    private String contactCountryCode;

    @NotBlank
    @Size(min = 8,max = 12)
    private String contactNumber;
}
