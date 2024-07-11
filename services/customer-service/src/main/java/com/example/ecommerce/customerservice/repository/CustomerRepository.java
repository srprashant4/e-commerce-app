package com.example.ecommerce.customerservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ecommerce.customerservice.document.CustomerDocument;

public interface CustomerRepository extends MongoRepository<CustomerDocument, String>{
    
}
