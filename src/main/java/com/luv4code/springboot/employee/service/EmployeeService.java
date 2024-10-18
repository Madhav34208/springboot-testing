package com.luv4code.springboot.employee.service;

import com.luv4code.springboot.employee.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> findAll();
    Employee updateEmployee(Employee employee);
    void deleteById(Integer id);
}
