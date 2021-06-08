package ru.realdating.project.model;

import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class TestUser {

    @Test
    public void createUserTest() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = factory.createEntityManager();

        User user = new User("admin", "admin");
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        User foundUser = entityManager.find(User.class, 1);
        assertNotNull(foundUser);

        User findByQuery = entityManager.createQuery("select u from User u where u.id = :id", User.class)
                .setParameter("id", user.getId())
                .getSingleResult();

        assertNotNull(findByQuery);

        entityManager.close();
        factory.close();
    }

}
