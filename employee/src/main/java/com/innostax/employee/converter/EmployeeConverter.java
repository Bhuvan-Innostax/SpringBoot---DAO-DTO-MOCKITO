package com.innostax.employee.converter;

import org.springframework.stereotype.Component;

import com.innostax.employee.Entity.Employee;
import com.innostax.employee.dto.EmployeeDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeConverter {

    public EmployeeDto entityToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setDepartment(employee.getDepartment());
        dto.setDrinkChoice(employee.getDrinkChoice());

        return dto;
    }

    public List<EmployeeDto> entityListToDto(List<Employee> employee) {
        return employee.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Employee DtoToEntity(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setDepartment(dto.getDepartment());
        employee.setDrinkChoice(dto.getDrinkChoice());

        return employee;
    }

    public List<Employee> DtoListToEntity(List<EmployeeDto> dto) {
        return dto.stream().map(this::DtoToEntity).collect(Collectors.toList());
    }
}
