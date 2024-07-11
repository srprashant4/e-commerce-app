package com.example.ecommerce.customerservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ecommerce.customerservice.document.CustomerDocument;
import com.example.ecommerce.customerservice.exceptions.CustomerNotFoundException;
import com.example.ecommerce.customerservice.repository.CustomerRepository;
import com.example.ecommerce.customerservice.request.CustomerRequest;
import com.example.ecommerce.customerservice.response.CustomerResponse;

import org.apache.commons.lang.StringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    // Dependency Injection for Repository.
    private CustomerRepository customerRepository;

    // Dependency Injection for Mapper Service Class.
    private CustomerMapper customerMapper;

    // Method to create a new customer entry.
    public String createCustomer(CustomerRequest customerRequestDto) {

        var customer = customerRepository.save(customerMapper.toCustomer(customerRequestDto));
        
        return customer.getId();
    }

    // Method to update the details of existing customer.
    public void updateCustomer(@Valid CustomerRequest customerRequestDto) {

        var customer = customerRepository.findById(customerRequestDto.id())
        .orElseThrow(() -> new CustomerNotFoundException(
            String.format("Cannot Update Customer:: No Customer Found with the provided ID:: %s", customerRequestDto.id())
            ));

        // This method is to ensure that we are not over-riding the details of an
        // existing customer.
        mergeCustomer(customer, customerRequestDto);

        customerRepository.save(customer);
    }

    // Method to return the list of all the customers.
    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
        .stream()
        .map(customerMapper::fromCustomer)
        .collect(Collectors.toList());
    }

    // Method to check if any customer entry exists for the provided customer-id.
    public Boolean existsById(String customerId) {
        
        return customerRepository.findById(customerId).isPresent();
    }

    // Method to find a customer record using the provided customer-id.
    public CustomerResponse findCustomerById(String customerId) {
        return customerRepository.findById(customerId)
        .map(customerMapper::fromCustomer)
        .orElseThrow(() -> new CustomerNotFoundException(
            String.format("No Customer Found with the provided ID:: %s", customerId)
            ));
    }

    // Method to delete a customer record based on the customer-id provided.
    public void deleteCustomerById(String customerId) {
        customerRepository.deleteById(customerId);
    }

    // Method to perform validations and ensure that the details for already
    // existing customers is not over-ridden.
    private void mergeCustomer(CustomerDocument customer, CustomerRequest customerRequestDto) {

        if(StringUtils.isNotBlank(customerRequestDto.firstName())) {
            customer.setFirstName(customerRequestDto.firstName());
        }

        if(StringUtils.isNotBlank(customerRequestDto.lastName())) {
            customer.setLastName(customerRequestDto.lastName());
        }

        if(StringUtils.isNotBlank(customerRequestDto.email())) {
            customer.setEmail(customerRequestDto.email());
        }

        if(null != customerRequestDto.address()) {
            customer.setAddress(customerRequestDto.address());
        }
    }
    
}
