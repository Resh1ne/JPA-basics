package org.example;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.example.entity.User;

import java.util.List;

public class Main {
    private static EntityManagerFactory factory;
    private static EntityManager manager;

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("psql");
        EntityManager manager = factory.createEntityManager();
        try {
            //READ
            User user = manager.find(User.class, 2L);
            System.out.println(user);

            //READ ALL
            TypedQuery<User> query = manager.createQuery("from User", User.class);
            List<User> users = query.getResultList();
            users.forEach(System.out::println);

            //CREATE
            manager.getTransaction().begin();
            User userCreate = new User();
            userCreate.setLogin("login1");
            userCreate.setPassword("password1");
            manager.persist(userCreate);
            manager.getTransaction().commit();
            System.out.println(userCreate);

            //UPDATE
            manager.getTransaction().begin();
            userCreate.setPassword("password2");
            manager.getTransaction().commit();

            //DELETE
            manager.getTransaction().begin();
            User userDelete = manager.find(User.class, 2L);
            manager.remove(userDelete);
            manager.getTransaction().commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            manager.close();
            if (factory != null && factory.isOpen()) {
                factory.close();
            }
        }
    }
}