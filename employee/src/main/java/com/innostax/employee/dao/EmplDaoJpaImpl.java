package com.innostax.employee.dao;

import com.innostax.employee.Entity.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;


@Repository
public class EmplDaoJpaImpl implements EmployeeDAO {

    private final EntityManager entityManager;

    @Autowired
    public EmplDaoJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        // Corrected the entity name case sensitivity in the query
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee findById(int id ){
        Employee getEmp = entityManager.find(Employee.class, id);
        return getEmp;
    }

    @Override
    @Transactional
    public Employee save(Employee newEmployee){
        Employee dbEmployee = entityManager.merge(newEmployee);
        return dbEmployee;
    }

    @Override
    @Transactional
    public String deleteById(int id){
        Employee emp = entityManager.find(Employee.class, id);
        entityManager.remove(emp);
        return "Employee is deleted";
    }
}
