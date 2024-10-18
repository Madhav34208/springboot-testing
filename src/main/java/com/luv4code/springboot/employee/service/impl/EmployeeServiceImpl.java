package com.luv4code.springboot.employee.service.impl;

import com.luv4code.springboot.employee.model.Employee;
import com.luv4code.springboot.employee.repository.EmployeeRepository;
import com.luv4code.springboot.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }
}
