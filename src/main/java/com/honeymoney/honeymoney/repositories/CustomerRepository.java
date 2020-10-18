package com.honeymoney.honeymoney.repositories;

import com.honeymoney.honeymoney.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUsername(String name);

    Optional<Customer> findByUsernameAndPassword(String name, String password);

    //void createCustomer(Customer customer);

}
