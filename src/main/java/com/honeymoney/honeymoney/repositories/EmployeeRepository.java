package com.honeymoney.honeymoney.repositories;

import com.honeymoney.honeymoney.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
