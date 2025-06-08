package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import org.example.entity.manyToOne.Employee;
import org.example.repository.EmployeeRepository;

import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private static final String GET_ALL = "from Employee";
    private final EntityManager manager;

    public EmployeeRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Employee find(Long key) {
        return manager.find(Employee.class, key);
    }

    @Override
    public List<Employee> findAll() {
        return manager.createQuery(GET_ALL, Employee.class).getResultList();
    }

    @Override
    public void save(Employee entity) {
        manager.getTransaction().begin();
        if (entity.getId() == null) {
            manager.persist(entity);
        } else {
            manager.merge(entity);
        }
        manager.getTransaction().commit();
    }

    @Override
    public boolean delete(Long key) {
        manager.getTransaction().begin();
        Employee employee = manager.find(Employee.class, key);
        if (employee == null) {
            return false;
        }
        manager.remove(employee);
        manager.getTransaction().commit();
        return true;
    }
}
