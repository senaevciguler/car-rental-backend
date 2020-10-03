package com.honeymoney.honeymoney.controllers;

import com.honeymoney.honeymoney.dto.CustomerDto;
import com.honeymoney.honeymoney.models.Customer;
import com.honeymoney.honeymoney.repositories.CustomerRepository;
import com.honeymoney.honeymoney.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;


    @GetMapping("/customers")
    public Result getCustomersList() {
        return Result.Success.builder()
                .payload(customerRepository.findAll())
                .build();
    }

    @GetMapping("/customers/{id}")
    public Result getCustomer(@PathVariable Long id) {
        return Result.Success.builder()
                .payload(customerRepository.findById(id))
                .build();
    }

    @DeleteMapping("/customers/{id}")
    public Result deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return Result.Success.builder()
                .message("Customer deleted successfully")
                .build();
    }

    @PutMapping("/customers/{id}")
    public Result updateCustomer(@PathVariable long id, @RequestBody Customer updateCustomer) {

        Customer updatedCustomer = customerRepository.findById(id).map(customer -> {
            customer.setName(updateCustomer.getName());
            customer.setLastName(updateCustomer.getLastName());
            customer.setCustomerId(updateCustomer.getCustomerId());
            return customerRepository.save(customer);
        }).orElseGet(() -> {
            updateCustomer.setId(id);
            return customerRepository.save(updateCustomer);
        });
        return Result.Success.builder()
                .message("Customer updated successfully")
                .payload(updatedCustomer)
                .build();
    }

    @PostMapping("/customers")
    public Result createCustomer(@RequestBody CustomerDto customerDto){
        Customer customer = Customer.builder()
                .name(customerDto.getName())
                .lastName(customerDto.getLastName())
                .customerId(customerDto.getCustomerId())
                .build();

        return Result.Success.builder()
                .message("Customer saved successfully")
                .payload(customerRepository.save(customer))
                .build();
    }
}
