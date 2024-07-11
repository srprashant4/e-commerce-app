package com.example.ecommerce.customerservice.service;

import org.springframework.stereotype.Service;

import com.example.ecommerce.customerservice.document.CustomerDocument;
import com.example.ecommerce.customerservice.request.CustomerRequest;
import com.example.ecommerce.customerservice.response.CustomerResponse;

@Service
public class CustomerMapper {

    public CustomerDocument toCustomer(CustomerRequest customerRequestDto) {
        
        if(null == customerRequestDto) {
            return null;
        }

        return CustomerDocument.builder()
        .id(customerRequestDto.id())
        .firstName(customerRequestDto.firstName())
        .lastName(customerRequestDto.lastName())
        .email(customerRequestDto.email())
        .address(customerRequestDto.address())
        .build();
    }

    public CustomerResponse fromCustomer(CustomerDocument document) {
        return new CustomerResponse(document.getId(), document.getFirstName(), document.getLastName(),
                document.getEmail(), document.getAddress());
    }
    
}
