package com.innostax.employee.service;

import com.innostax.employee.Entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.jupiter.api.Assertions.*;
import com.innostax.employee.config.EmployeeApplication;
import com.innostax.employee.dao.EmployeeDAO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = EmployeeApplication.class)
@ActiveProfiles("test")
@Sql(scripts = "/com/innostax/employee/config/schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class EmployeeTest {



    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmployeeDAO employeeDAO;

    @BeforeEach
    public void setUp() {
        restTemplate = new TestRestTemplate();

    }

    @Test
    public void testAddEmployee() {
        Employee employee = new Employee("Rahul", "HR", "coffee");

        // Ensure restTemplate is not null before making a request
        assertNotNull(restTemplate, "RestTemplate is not initialized");
        String baseUrl = "http://localhost:" + String.valueOf(1) + "/employee";
        Employee response = restTemplate.postForObject(baseUrl, employee, Employee.class);

        assertNotNull(response, "Response should not be null");
        assertEquals("Rahul", response.getName());
        assertEquals(1, employeeDAO.findAll().size());
    }

    @Test
    @Sql(scripts = "/com/innostax/employee/config/insert.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/com/innostax/employee/config/delete.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetEmployees() {
        String baseUrl = "http://localhost:" + String.valueOf(1) + "/employee";
        Employee[] employees = restTemplate.getForObject(baseUrl, Employee[].class);

        assertNotNull(employees, "Employees list should not be null");
        assertEquals(1, employees.length);
        assertEquals(1, employeeDAO.findAll().size());
    }
}
