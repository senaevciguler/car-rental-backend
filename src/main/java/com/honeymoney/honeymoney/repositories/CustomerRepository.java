package com.honeymoney.honeymoney.repositories;

import com.honeymoney.honeymoney.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
