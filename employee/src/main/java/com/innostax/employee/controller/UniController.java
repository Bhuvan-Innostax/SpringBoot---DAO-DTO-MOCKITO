package com.innostax.employee.controller;

import com.innostax.employee.dao.EmployeeDAO;
import com.innostax.employee.dto.EmployeeDto;
import com.innostax.employee.converter.EmployeeConverter;

import org.springframework.web.bind.annotation.*;


import java.util.List;

import com.innostax.employee.Entity.Employee;


@RestController
@RequestMapping("/employee")
public class UniController {

    private final EmployeeDAO employeeDAO;
    private final EmployeeConverter employeeConverter;

    public UniController(EmployeeDAO employeeDAO , EmployeeConverter employeeConverter) {
        this.employeeDAO = employeeDAO;
        this.employeeConverter = employeeConverter;
    }

    @GetMapping("/")
    public List<EmployeeDto> getAll() {
        List<Employee> employees = employeeDAO.findAll();
        return employeeConverter.entityListToDto(employees);
    }

    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable int id) {
        Employee employee = employeeDAO.findById(id);
        return employeeConverter.entityToDto(employee);
    }

    @PostMapping("/add")
    public EmployeeDto save(@RequestBody EmployeeDto EmployeeDto) {
        Employee employee = employeeConverter.DtoToEntity(EmployeeDto);
        employee = employeeDAO.save(employee);
        return employeeConverter.entityToDto(employee);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        return employeeDAO.deleteById(id);
    }
}


