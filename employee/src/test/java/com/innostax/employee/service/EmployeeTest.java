package com.innostax.employee.service;

import com.innostax.employee.Entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;
import com.innostax.employee.config.EmployeeApplication;
import com.innostax.employee.dao.EmployeeDAO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = EmployeeApplication.class)
@ActiveProfiles("test")
@Sql(scripts = "/schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class EmployeeTest {

    @LocalServerPort
    private int port;

    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmployeeDAO employeeDAO;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/employee";
    }

    @Test
    public void testAddEmployee() {
        Employee employee = new Employee("Rahul", "HR", "coffee");

        // Ensure restTemplate is not null before making a request
        assertNotNull(restTemplate, "RestTemplate is not initialized");

        Employee response = restTemplate.postForObject(baseUrl, employee, Employee.class);

        assertNotNull(response, "Response should not be null");
        assertEquals("Rahul", response.getName());
        assertEquals(1, employeeDAO.findAll().size());
    }

    @Test
    @Sql(statements = "INSERT INTO employee (id, name, department, drink_choice) VALUES (4, 'Rahul', 'HR', 'TEA')",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM employee WHERE name='Rahul'",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetEmployees() {
        Employee[] employees = restTemplate.getForObject(baseUrl, Employee[].class);

        assertNotNull(employees, "Employees list should not be null");
        assertEquals(1, employees.length);
        assertEquals(1, employeeDAO.findAll().size());
    }
}
