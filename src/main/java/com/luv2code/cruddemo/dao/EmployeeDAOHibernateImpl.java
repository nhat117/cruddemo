package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
    //Field for entity manager
    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional //For transaction management
    public List<Employee> findAll() {
        //Get current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        //Create Query
        Query<Employee> query = currentSession.createQuery("from Employee",Employee.class);
        //Execute query and return the result list
        List<Employee> employees = query.getResultList();
        //Return results
        return employees;
    }

    @Override
    public Employee findByID(int theID) {
        //Get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        //Get the employee
        Employee employee = currentSession.get(Employee.class, theID);
        return employee;
    }


    @Override
    public void save(Employee theEmployee) {
        //Current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
//        if id = 0 save else update
        currentSession.saveOrUpdate(theEmployee);
    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeID");
        theQuery.setParameter("employeeID", id);
       theQuery.executeUpdate();
    }
}
