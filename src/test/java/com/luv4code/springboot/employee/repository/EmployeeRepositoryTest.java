package com.luv4code.springboot.employee.repository;

import com.luv4code.springboot.employee.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;
    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder().firstName("Madhav").lastName("Ponnana").email("Madhav.p.akv@gmail.com").build();
    }

    @Test
    @DisplayName("Saved Employee Test")
    void testSaveEmployee() {
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
        //when
        repository.save(employee);
        Employee savedEmployee = repository.findById(employee.getEmployeeId()).orElse(null);

        //then
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getFirstName()).isEqualTo("Madhav");
        assertThat(savedEmployee.getEmployeeId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Find Employee By Email")
    void testFindEmployeeByEamil() {
        //when
        repository.save(employee);
        Employee savedEmployee = repository.findByEmail(employee.getEmail());

        //then
        assertThat(savedEmployee.getEmail()).isEqualTo("Madhav.p.akv@gmail.com");
        assertThat(savedEmployee.getEmail()).isNotEqualTo("madhav@outlook.com");
    }

    @Test
    @DisplayName("Update Employee By Id")
    void testUpdateEmployeeById() {
        //when
        repository.save(employee);
        Employee savedEmployee = repository.findById(employee.getEmployeeId()).orElse(null);
        savedEmployee.setFirstName("Madhav");
        savedEmployee.setLastName("Ponnana");

        Employee updatedEmployee = repository.save(savedEmployee);

        //then
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Madhav");
        assertThat(updatedEmployee.getLastName()).isEqualTo("Ponnana");
    }

    @Test
    @DisplayName("Delete Employee")
    void deleteEmployee() {
        //given
        Employee savedEmployee = repository.save(employee);

        //when
        repository.delete(savedEmployee);
        Optional<Employee> optionalEmployee = repository.findById(savedEmployee.getEmployeeId());

        //then
        assertThat(optionalEmployee).isEmpty();

    }

    @Test
    @DisplayName("FindEByJPQLIndexParams")
    void testFindEByJPQLIndexParams(){
        //when
        Employee savedEmployee = repository.save(employee);
        Employee returnedEmployee = repository.findByJPQLIndexParams(savedEmployee.getFirstName(), savedEmployee.getLastName());

        //then
        assertThat(returnedEmployee).isNotNull();
    }

    @Test
    @DisplayName("FindEByJPQLNamedParams")
    void testFindEByJPQLNamedParams(){
        //when
        Employee savedEmployee = repository.save(employee);
        Employee returnedEmployee = repository.findByJPQLNamedParams(savedEmployee.getFirstName(), savedEmployee.getLastName());

        //then
        assertThat(returnedEmployee).isNotNull();
    }

    @Test
    @DisplayName("FindEByIndexedIndexParams")
    void testFindEByNativeIndexParams(){
        //when
        Employee savedEmployee = repository.save(employee);
        Employee returnedEmployee = repository.findByNativeIndexedParams(savedEmployee.getFirstName(), savedEmployee.getLastName());

        //then
        assertThat(returnedEmployee).isNotNull();
    }

    @Test
    @DisplayName("FindEByJPQLNamedParams")
    void testFindEByNativeNamedParams(){
        //when
        Employee savedEmployee = repository.save(employee);
        Employee returnedEmployee = repository.findByNativeNamedParams(savedEmployee.getFirstName(), savedEmployee.getLastName());

        //then
        assertThat(returnedEmployee).isNotNull();
    }
}