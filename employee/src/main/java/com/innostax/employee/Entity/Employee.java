package com.innostax.employee.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Getter  
@Setter  
public class Employee {

    @Id
    public int id; 

    @Column(name = "employee_name")
    public String name;  

    @Column(name = "department")
    public String department;  

    @Column(name = "drink_choice")
    public String drinkChoice;  


    // Default constructor
    public Employee() {}

    // Parameterized constructor
    public Employee(String name, String department, String drinkChoice) {
        this.name = name;
        this.department = department;
        this.drinkChoice = drinkChoice;
    }
}
