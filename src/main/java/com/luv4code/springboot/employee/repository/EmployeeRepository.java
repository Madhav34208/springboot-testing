package com.luv4code.springboot.employee.repository;

import com.luv4code.springboot.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
