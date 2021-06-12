package ru.realdating.project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.realdating.project.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
public class UserDao {

    @Autowired
    private EntityManager entityManager;

    public User createUser(String login, String password) {
        User user = new User(login, password);

        entityManager.getTransaction().begin();
        try {
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }

        return user;
    }

    public User findUserByLogin(String login) {
        try {
            return entityManager.createQuery("select u from User u where u.login = :loginForSearch", User.class)
                    .setParameter("loginForSearch", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User findUserByLoginAndPassword(String login, String password) {
        try {
            return entityManager.createQuery("select u from User u " +
                    "where u.login = :loginForSearch and u.password = :passwordForSearch", User.class)
                    .setParameter("loginForSearch", login)
                    .setParameter("passwordForSearch", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
