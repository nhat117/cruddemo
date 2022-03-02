package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    public List<Employee> findAll();

    public Employee findByID(int theID);

    public void save(Employee theEmployee);

    public void delete(int id);
}
