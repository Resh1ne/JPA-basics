package org.example.demo;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Main {
    private static EntityManagerFactory factory;
    private static EntityManager manager;

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("psql");
        EntityManager manager = factory.createEntityManager();
        try {
            //READ
            Demo demo = manager.find(Demo.class, 2L);
            System.out.println(demo);

            //READ ALL
            TypedQuery<Demo> query = manager.createQuery("from Demo", Demo.class);
            List<Demo> demos = query.getResultList();
            demos.forEach(System.out::println);

            //CREATE
            manager.getTransaction().begin();
            Demo demoCreate = new Demo();
            demoCreate.setLogin("login1");
            demoCreate.setPassword("password1");
            manager.persist(demoCreate);
            manager.getTransaction().commit();
            System.out.println(demoCreate);

            //UPDATE
            manager.getTransaction().begin();
            demoCreate.setPassword("password2");
            manager.getTransaction().commit();

            //DELETE
            manager.getTransaction().begin();
            Demo demoDelete = manager.find(Demo.class, 2L);
            manager.remove(demoDelete);
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