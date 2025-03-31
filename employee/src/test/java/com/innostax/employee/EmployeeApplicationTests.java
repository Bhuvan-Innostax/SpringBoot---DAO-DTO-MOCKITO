package com.innostax.employee;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import com.innostax.employee.config.EmployeeApplication;

@SpringBootTest
@ContextConfiguration(classes = EmployeeApplication.class)
@TestPropertySource(properties = {
    "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.HSQLDialect",
    "spring.datasource.url=jdbc:hsqldb:mem:testdb",
    "spring.datasource.driver-class-name=org.hsqldb.jdbc.JDBCDriver",
    "spring.datasource.username=SA",
    "spring.datasource.password=",
    

})
@ComponentScan(basePackages = "com.innostax.employee") 
class EmployeeApplicationTests {
    @Test
    void contextLoads() {
    }
}
