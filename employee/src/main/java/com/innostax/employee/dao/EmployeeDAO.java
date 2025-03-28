package com.innostax.employee.dao;

import com.innostax.employee.Entity.Employee;
import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();
    
    Employee findById(int id);

    Employee save(Employee newEmployee);

    String deleteById(int id);
}
