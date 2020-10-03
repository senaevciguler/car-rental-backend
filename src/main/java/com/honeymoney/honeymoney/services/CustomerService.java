package com.honeymoney.honeymoney.services;

import com.honeymoney.honeymoney.dto.CustomerDto;
import com.honeymoney.honeymoney.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import searchs.CustomerSearchForm;

import java.util.Optional;

public interface CustomerService {
    CustomerDto newCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(CustomerDto customerDto);
    Page<CustomerDto> findCustomer(CustomerSearchForm customerSearchForm, Pageable pageable);
    Optional<CustomerDto> findCustomerById(long id);
    Result deleteCustomerById(long id);
}
