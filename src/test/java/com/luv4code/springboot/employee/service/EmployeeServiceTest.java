package com.luv4code.springboot.employee.service;

import com.luv4code.springboot.employee.model.Employee;
import com.luv4code.springboot.employee.repository.EmployeeRepository;
import com.luv4code.springboot.employee.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeServiceTest {

    private EmployeeRepository repository;
    private EmployeeService service;
    private Employee employee;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(EmployeeRepository.class);
        service = new EmployeeServiceImpl(repository);
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
        //stub -creation
        BDDMockito.given(repository.save(employee)).willReturn(employee);
        Employee storedEmployee = service.saveEmployee(employee);

        //then
        assertThat(storedEmployee).isNotNull();
    }
}