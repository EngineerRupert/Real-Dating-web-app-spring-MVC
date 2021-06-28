package ru.realdating.project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.realdating.project.model.User;
import ru.realdating.project.model.UserCard;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
public class UserCardDao {

    private EntityManager entityManager;

    @Autowired
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

    @Transactional
    public UserCard createUserCard(User user) {
        UserCard userCard = new UserCard();
            entityManager.persist(userCard);
            userCard.setUserId(user);
        return userCard;
    }
// old without @Transactional
//    public UserCard createUserCard(User user) {
//        UserCard userCard = new UserCard();
//        entityManager.getTransaction().begin();
//        try {
//            entityManager.persist(userCard);
//            userCard.setUserId(user);
//            entityManager.getTransaction().commit();
//        } catch (Exception e) {
//            entityManager.getTransaction().rollback();
//            throw e;
//        }
//        return userCard;
//    }

    @Transactional
    public UserCard refreshMainInfoUserCard(UserCard userCard) {
            entityManager.persist(userCard);
        return userCard;
    }
// old without @Transactional
//    public UserCard refreshMainInfoUserCard(UserCard userCard) {
//        entityManager.getTransaction().begin();
//        try {
//            entityManager.persist(userCard);
//            entityManager.getTransaction().commit();
//        } catch (Exception e) {
//            entityManager.getTransaction().rollback();
//            throw e;
//        }
//        return userCard;
//    }

    @Transactional
    public UserCard addLike(UserCard userCard) {
        userCard.setLikeUserCard(userCard.getLikeUserCard()+1);
            entityManager.persist(userCard);
        return userCard;
    }
    // old without @Transactional
//    public UserCard addLike(UserCard userCard) {
//        userCard.setLikeUserCard(userCard.getLikeUserCard()+1);
//        entityManager.getTransaction().begin();
//        try {
//            entityManager.persist(userCard);
//            entityManager.getTransaction().commit();
//        } catch (Exception e) {
//            entityManager.getTransaction().rollback();
//            throw e;
//        }
//        return userCard;
//    }

}
