package com.example.ecommerce.customerservice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.customerservice.request.CustomerRequest;
import com.example.ecommerce.customerservice.response.CustomerResponse;
import com.example.ecommerce.customerservice.service.CustomerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(
        @RequestBody @Valid CustomerRequest customerRequestDto) {
        
        return ResponseEntity.ok(customerService.createCustomer(customerRequestDto));
    }
    
    @PutMapping
    public ResponseEntity<Void> updateCustomer(
        @RequestBody @Valid CustomerRequest customerRequestDto) {
            customerService.updateCustomer(customerRequestDto);

            return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAllCustomerData() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }
    
    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existsById(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.existsById(customerId));
    }
    
    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.findCustomerById(customerId));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteById(@PathVariable String customerId) {
        return ResponseEntity.accepted().build();
    }
}
