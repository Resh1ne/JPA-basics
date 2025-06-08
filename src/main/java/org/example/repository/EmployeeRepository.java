package org.example.repository;

import org.example.entity.manyToOne.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
