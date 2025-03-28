package com.innostax.employee.service;

import com.innostax.employee.controller.UniController;
import com.innostax.employee.dto.EmployeeDto;
import com.innostax.employee.Entity.Employee;
import com.innostax.employee.converter.EmployeeConverter;
import com.innostax.employee.dao.EmployeeDAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class EmployeeSaveTest {

    @Mock
    private EmployeeDAO employeeDAO; 

    @Mock
    private EmployeeConverter EmployeeConverter; 

    @InjectMocks
    private UniController uniController; 

    private EmployeeDto employeeDto;
    private Employee employee;

    @BeforeEach
    public void setUp() {
        employeeDto = new EmployeeDto();
        employeeDto.setName("Jatin");
        employeeDto.setDepartment("HR");
        employeeDto.setDrinkChoice("Coffee");

        employee = new Employee("Jatin", "HR", "Coffee");
        employee.setId(1);
    }

    @Test
    public void testSaveEmployee() {
        System.out.println("First Test Case is running . . . ");
        when(EmployeeConverter.DtoToEntity(employeeDto)).thenReturn(employee);
        when(employeeDAO.save(employee)).thenReturn(employee);
        when(EmployeeConverter.entityToDto(employee)).thenReturn(employeeDto);

        EmployeeDto returnedDto = uniController.save(employeeDto);

        assertNotNull(returnedDto);
        // assertEquals(1, returnedDto.getId()); 
        assertEquals("Jatin", returnedDto.getName()); 
        assertEquals("Coffee", returnedDto.getDrinkChoice()); 
        System.out.println("Test Case Passed !");
    }
}
