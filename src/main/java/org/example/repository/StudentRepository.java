package org.example.repository;

import org.example.entity.manyToMany.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
