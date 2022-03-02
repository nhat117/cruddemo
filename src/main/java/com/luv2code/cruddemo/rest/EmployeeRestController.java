package com.luv2code.cruddemo.rest;

import com.luv2code.cruddemo.entity.Employee;
import com.luv2code.cruddemo.service.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeServices employeeServices;
    //Delegate to employee service
    @Autowired
    public EmployeeRestController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }

    //Expose "/employees" and return list of employee
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeServices.findAll();
    }

    //ADD mapping for GET /employees/employeeId

    @GetMapping("/employees/{employeeID}")
    public Employee getEmployee(@PathVariable int employeeID) {
        Employee employee = employeeServices.findById(employeeID);
        if (employee == null) {
            throw new RuntimeException("Employee with id " + employeeID + "not found");
        }
        return employee;
    }

//    POST Methode
    //Add mapping for POST /employees- add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        //Just in case pass id into JSON return 0
        //Handle binding as JSON
        //Force save of a new Item
        employeeServices.save(employee);
        return employee;
    }

    //Add mapping for PUT update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        employeeServices.save(employee);
        return employee;
    }

    @DeleteMapping("/employees/{employeeID}")
    public String deleteEmployee(@PathVariable int employeeID) {
        Employee tmp = employeeServices.findById(employeeID);
        if(tmp == null) {
            throw new RuntimeException("Employee with id : " + employeeID + "not exist");
        }
        employeeServices.delete(employeeID);
        return "Delete Success";
    }

}
