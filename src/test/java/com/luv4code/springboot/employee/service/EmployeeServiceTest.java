package com.luv4code.springboot.employee.service;

import com.luv4code.springboot.employee.exception.EmployeeExistsException;
import com.luv4code.springboot.employee.model.Employee;
import com.luv4code.springboot.employee.repository.EmployeeRepository;
import com.luv4code.springboot.employee.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl service;
    private Employee employee;

    @BeforeEach
    void setUp() {
        employee =
                Employee.builder()
                        .employeeId(1)
                        .firstName("Madhav")
                        .lastName("Ponnana")
                        .email("madhav@gmail.com")
                        .build();
    }

    @Test
    @DisplayName("Save Employee")
    void testSaveEmployee() {
        //when
        given(repository.save(employee)).willReturn(employee);
        Employee storedEmployee = service.saveEmployee(employee);

        //then
        assertThat(storedEmployee).isNotNull();
    }

    @Test
    @DisplayName("Employee email already exists")
    void testEmailExists() {
        //when
        given(repository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));
        assertThrows(EmployeeExistsException.class, () ->
                service.saveEmployee(employee));

        //then
        verify(repository, never()).save(any(Employee.class));
    }

    @Test
    @DisplayName("Find All Employees")
    void testFindAllEmployees() {
        //given
        Employee employee1 = Employee.builder().employeeId(2).firstName("Roja").lastName("Tumma").email("roja@gmail.com").build();
        given(repository.findAll()).willReturn(List.of(employee, employee1));

        //when
        List<Employee> employeeList = service.findAll();

        //then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Update Employee")
    void testUpdateEmployee() {
        //given
        given(repository.save(employee)).willReturn(employee);
        employee.setFirstName("Maddy");
        employee.setLastName("P");

        //when
        Employee savedEmployee = service.updateEmployee(employee);

        //then
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getFirstName()).isEqualTo("Maddy");
    }

    @Test
    @DisplayName("Delete Employee by ID")
    void testDeleteEmployeeById() {
        //given
        willDoNothing().given(repository).deleteById(employee.getEmployeeId());

        //when
        service.deleteById(employee.getEmployeeId());

        //then
        verify(repository, times(1)).deleteById(employee.getEmployeeId());
    }
}