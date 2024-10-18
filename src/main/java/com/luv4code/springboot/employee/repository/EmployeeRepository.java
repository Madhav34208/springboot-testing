package com.luv4code.springboot.employee.repository;

import com.luv4code.springboot.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmail(String email);

    @Query("select e from Employee  e where e.firstName=?1 and e.lastName=?2")
    Employee findByFirstNameAndLastName(String firstName, String lastName);

}
