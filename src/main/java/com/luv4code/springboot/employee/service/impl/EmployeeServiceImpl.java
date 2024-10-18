package com.luv4code.springboot.employee.service.impl;

import com.luv4code.springboot.employee.exception.EmployeeExistsException;
import com.luv4code.springboot.employee.model.Employee;
import com.luv4code.springboot.employee.repository.EmployeeRepository;
import com.luv4code.springboot.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        Optional<Employee> emp = repository.findByEmail(employee.getEmail());

        if(emp.isPresent())
            throw new EmployeeExistsException("Employee already present with this email id: "+employee.getEmail());

        return repository.save(employee);
    }
}
