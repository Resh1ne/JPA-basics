package org.example;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.example.demo.Demo;
import org.example.entity.manyToMany.Section;
import org.example.entity.manyToMany.Student;
import org.example.entity.manyToOne.Department;
import org.example.entity.manyToOne.Employee;
import org.example.repository.EmployeeRepository;
import org.example.repository.SectionRepository;
import org.example.repository.StudentRepository;
import org.example.repository.UserRepository;
import org.example.repository.impl.EmployeeRepositoryImpl;
import org.example.repository.impl.SectionRepositoryImpl;
import org.example.repository.impl.StudentRepositoryImpl;
import org.example.repository.impl.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("psql");
        EntityManager manager = factory.createEntityManager();
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl(manager);
        SectionRepository sectionRepository = new SectionRepositoryImpl(manager);
        StudentRepository studentRepository = new StudentRepositoryImpl(manager);
        UserRepository userRepository = new UserRepositoryImpl(manager);
        try {
//            //READ
//            //Employee
//            Employee employee = employeeRepository.find(25L);
//            System.out.println(employee);
//            //Section
//            //Student
//            Student student = studentRepository.find(14L);
//            System.out.println(student);
//            //User

//            //READ ALL
//            //Employee
//            List<Employee> employees = employeeRepository.findAll();
//            System.out.println(employees);
//            //Section
//            //Student
//            List<Student> students = studentRepository.findAll();
//            System.out.println(students);
//            //User

//            //CREATE
//            //Employees
//            Employee employeeCreate = new Employee();
//            employeeCreate.setFirstName("1");
//            employeeCreate.setLastName("2");
//            employeeCreate.setDepartment(...);
//            employeeRepository.save(employeeCreate);
//            //Section
//            //Students
//            Student studentCreate = new Student();
//            studentCreate.setFirstName("2");
//            studentCreate.setLastname("2");
//            List<Section> sectionsForStudent = new ArrayList<>();
//            sectionsForStudent.add(sectionRepository.find(1L));
//            sectionsForStudent.add(sectionRepository.find(2L));
//            studentCreate.setSections(sectionsForStudent);
//            studentRepository.save(studentCreate);
//            //User

//            //UPDATE
//            //Employees
//            Employee employeeForUpdated = employeeRepository.find(1L);
//            employeeForUpdated.setFirstName("Vova");
//            employeeRepository.save(employeeForUpdated);
//            //Section
//            //Students
//            Student studentForUpdate = studentRepository.find(1L);
//            studentForUpdate.setLastname("update");
//            studentForUpdate.setFirstName("update");
//            List<Section> sectionsForUpdateStudent = new ArrayList<>();
//            sectionsForUpdateStudent.add(sectionRepository.find(1L));
//            sectionsForUpdateStudent.add(sectionRepository.find(5L));
//            studentForUpdate.setSections(sectionsForUpdateStudent);
//            studentRepository.save(studentForUpdate);
//            //User


//            //DELETE
//            //Employee
//            employeeRepository.delete(1L);
//            //Section
//            //Student
//            studentRepository.delete(1L);
//            //User

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            manager.close();
            if (factory.isOpen()) {
                factory.close();
            }
        }
    }
}