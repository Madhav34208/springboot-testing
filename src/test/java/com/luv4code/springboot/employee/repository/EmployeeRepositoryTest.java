package com.luv4code.springboot.employee.repository;

import com.luv4code.springboot.employee.model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    @Test
    @DisplayName("Saved Employee Test")
    void testSaveEmployee() {
        //given
        Employee employee = Employee.builder().firstName("Madhav").lastName("Ponnana").email("Madhav.p.akv@gmail.com").build();

        //when
        Employee savedEmployee = repository.save(employee);

        //then
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getEmployeeId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Find All Employees")
    void testFindAllEmployees() {
        //given
        Employee e1 = Employee.builder().firstName("madhav").lastName("ponnana").email("madhav@gmail.com").build();
        Employee e2 = Employee.builder().firstName("roja").lastName("tumma").email("roja@gmail.com").build();
        repository.save(e1);
        repository.save(e2);

        //when
        List<Employee> employeeList = repository.findAll();

        //then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Find Employee By Id")
    void testFindEmployeeById() {
        //given
        Employee e1 = Employee.builder().firstName("madhav").lastName("ponnana").email("madhav@gmail.com").build();
        repository.save(e1);

        //when
        Employee savedEmployee = repository.findById(e1.getEmployeeId()).orElse(null);

        //then
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getFirstName()).isEqualTo("madhav");
        assertThat(savedEmployee.getEmployeeId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Find Employee By Email")
    void testFindEmployeeByEamil(){
        //given
        Employee e1 = Employee.builder().firstName("madhav").lastName("ponnana").email("madhav@gmail.com").build();
        repository.save(e1);

        //when
        Employee employee = repository.findByEmail(e1.getEmail());

        //then
        assertThat(employee.getEmail()).isEqualTo("madhav@gmail.com");
        assertThat(employee.getEmail()).isNotEqualTo("madhav@outlook.com");
    }
}