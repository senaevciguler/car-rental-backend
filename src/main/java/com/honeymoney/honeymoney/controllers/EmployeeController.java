package com.honeymoney.honeymoney.controllers;

import com.honeymoney.honeymoney.dto.EmployeeDto;
import com.honeymoney.honeymoney.models.Employee;
import com.honeymoney.honeymoney.repositories.EmployeeRepository;
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

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;


    @GetMapping("/employees")
    public Result getEmployeesList() {
        return Result.Success.builder()
                .payload(employeeRepository.findAll())
                .build();
    }

    @GetMapping("/employees/{id}")
    public Result getEmployee(@PathVariable Long id) {
        return Result.Success.builder()
                .payload(employeeRepository.findById(id))
                .build();
    }

    @DeleteMapping("/employees/{id}")
    public Result deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return Result.Success.builder()
                .message("Employee deleted successfully")
                .build();
    }

    @PutMapping("/employees/{id}")
    public Result updateEmployee(@PathVariable long id, @RequestBody Employee updateEmployee) {

        Employee updatedEmployee = employeeRepository.findById(id).map(employee -> {
            employee.setName(updateEmployee.getName());
            employee.setLastName(updateEmployee.getLastName());
            employee.setEmployeeId(updateEmployee.getEmployeeId());
            return employeeRepository.save(employee);
        }).orElseGet(() -> {
            updateEmployee.setId(id);
            return employeeRepository.save(updateEmployee);
        });
        return Result.Success.builder()
                .message("Employee updated successfully")
                .payload(updatedEmployee)
                .build();
    }

    @PostMapping("/employees")
    public Result createEmployee(@RequestBody EmployeeDto employeeDto){
        Employee employee = Employee.builder()
                .name(employeeDto.getName())
                .lastName(employeeDto.getLastName())
                .employeeId(employeeDto.getEmployeeId())
                .build();

        return Result.Success.builder()
                .message("Employee saved successfully")
                .payload(employeeRepository.save(employee))
                .build();
    }
}
