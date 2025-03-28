package com.innostax.employee.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmployeeDto {

    private int id;
    private String name; 
    private String department; 
    private String drinkChoice; 

}
