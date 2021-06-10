package ru.realdating.project.dao;

import ru.realdating.project.model.User;
import ru.realdating.project.model.UserCard;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UserCardDao {

    private final EntityManager entityManager;

    public UserCardDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public UserCard findUserCard(int id) {
        try {
            return entityManager.createQuery("from UserCard u where u.userId.id = :userId", UserCard.class)
                    .setParameter("userId", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public UserCard createUserCard(User user) {
        UserCard userCard = new UserCard();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(userCard);
            userCard.setUserId(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
        return userCard;
    }

    public UserCard refreshMainInfoUserCard(UserCard userCard) {
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(userCard);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
        return userCard;
    }

    public UserCard addLike(UserCard userCard) {
        userCard.setLikeUserCard(userCard.getLikeUserCard()+1);
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(userCard);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
        return userCard;
    }

}
