package org.example.demo;

import jakarta.persistence.EntityManager;

import java.util.List;

public class DemoRepositoryImpl implements DemoRepository {
    public static final String GET_ALL = "from Demo";
    private final EntityManager manager;

    public DemoRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Demo find(Long key) {
        return manager.find(Demo.class, key);
    }

    @Override
    public List<Demo> findAll() {
        return manager.createQuery(GET_ALL, Demo.class).getResultList();
    }

    @Override
    public void save(Demo entity) {
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
        Demo entity = manager.find(Demo.class, key);
        if (entity == null) {
            return false;
        }
        manager.remove(entity);
        manager.getTransaction().commit();
        return true;
    }
}
