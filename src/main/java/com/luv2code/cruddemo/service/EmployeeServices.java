package com.luv2code.cruddemo.service;

import com.luv2code.cruddemo.entity.Employee;

import java.util.List;


public interface EmployeeServices {
    public List<Employee> findAll();

    public Employee findById(int theId);

    public void save(Employee employee);

    public void delete(int id);
}
