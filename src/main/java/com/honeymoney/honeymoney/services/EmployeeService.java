package com.honeymoney.honeymoney.services;

import com.honeymoney.honeymoney.dto.EmployeeDto;
import com.honeymoney.honeymoney.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import searchs.EmployeeSearchForm;

import java.util.Optional;

public interface EmployeeService {
    EmployeeDto newEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployee(EmployeeDto employeeDto);
    Page<EmployeeDto> findEmployee(EmployeeSearchForm employeeSearchForm, Pageable pageable);
    Optional<EmployeeDto> findEmployeeById(long id);
    Result deleteEmployeeById(long id);
}
