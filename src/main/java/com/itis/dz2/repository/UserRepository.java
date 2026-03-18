package com.itis.dz2.repository;

import com.itis.dz2.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(UserEntity user) {
        entityManager.persist(user);
    }

    public UserEntity findById(Long id) {
        return entityManager.find(UserEntity.class, id);
    }

    public void update(UserEntity user) {
        entityManager.merge(user);
    }

    public void delete(Long id) {
        UserEntity user = entityManager.find(UserEntity.class, id);

        if (user != null) {
            entityManager.remove(user);
        }
    }
}