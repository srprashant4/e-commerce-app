package com.example.ecommerce.customerservice.request;

import com.example.ecommerce.customerservice.document.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
    String id,

    @NotNull(message = "Customer first name is a required field")
    String firstName,

    @NotNull(message = "Customer last name is a required field")
    String lastName,

    @NotNull(message = "Customer email is a required field")
    @Email(message = "Customer Email is invalid")
    String email,
    
    Address address
) {

}
