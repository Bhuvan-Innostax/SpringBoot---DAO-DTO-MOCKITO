package com.innostax.employee.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.innostax.employee.dao.EmployeeDAO;
import com.innostax.employee.Entity.Employee;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/employee")
public class UniController {

    private final EmployeeDAO employeeDao;

    public UniController(EmployeeDAO employeeDAO) {
        this.employeeDao = employeeDAO;
    }

    @GetMapping("")
    public List<Employee> getAll() {
        return employeeDao.findAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable int id) {
        return employeeDao.findById(id);
    }

    @PostMapping("/add")
    public Employee addNewEmployee(@RequestBody Employee newEmployee) {

        employeeDao.save(newEmployee);
        
        return newEmployee;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id ){
        return employeeDao.deleteById(id);
    }
    
}
