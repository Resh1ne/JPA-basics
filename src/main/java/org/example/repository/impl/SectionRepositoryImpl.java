package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import org.example.entity.manyToMany.Section;
import org.example.repository.SectionRepository;

import java.util.List;

public class SectionRepositoryImpl implements SectionRepository {
    private final static String GET_ALL = "from Section";
    private final EntityManager manager;

    public SectionRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Section find(Long key) {
        return manager.find(Section.class, key);
    }

    @Override
    public List<Section> findAll() {
        return manager.createQuery(GET_ALL, Section.class).getResultList();
    }

    @Override
    public void save(Section entity) {
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
        Section section = manager.find(Section.class, key);
        if (section == null) {
            return false;
        }
        manager.remove(section);
        manager.getTransaction().commit();
        return false;
    }
}
