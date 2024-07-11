package com.example.ecommerce.customerservice.response;

import com.example.ecommerce.customerservice.document.Address;

public record CustomerResponse(
    String id,

    String firstName,

    String lastName,

    String email,
    
    Address address
) {
    
}
