package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import org.example.entity.manyToMany.Student;
import org.example.repository.StudentRepository;

import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    private static final String GET_ALL = "from Student";
    private final EntityManager manager;

    public StudentRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Student find(Long key) {
        return manager.find(Student.class, key);
    }

    @Override
    public List<Student> findAll() {
        return manager.createQuery(GET_ALL, Student.class).getResultList();
    }

    @Override
    public void save(Student entity) {
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
        Student student = manager.find(Student.class, key);
        if (student == null) {
            return false;
        }
        manager.remove(student);
        manager.getTransaction().commit();
        return true;
    }
}
