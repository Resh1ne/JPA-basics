package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import org.example.entity.oneToOne.User;
import org.example.repository.UserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    public static final String GET_ALL = "from User";
    private final EntityManager manager;

    public UserRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }


    @Override
    public User find(Long key) {
        return manager.find(User.class, key);
    }

    @Override
    public List<User> findAll() {
        return manager.createQuery(GET_ALL, User.class).getResultList();
    }

    @Override
    public void save(User entity) {
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
        User user = manager.find(User.class, key);
        if (user == null) {
            return false;
        }
        manager.remove(user);
        manager.getTransaction().commit();
        return true;
    }
}
