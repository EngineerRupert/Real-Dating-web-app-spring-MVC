package ru.realdating.project.dao;

import ru.realdating.project.model.User;
import ru.realdating.project.model.UserCard;

import javax.persistence.EntityManager;

public class UserCardDao {

    private final EntityManager entityManager;

    public UserCardDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public UserCard createMainInfoUser(User user, String aboutMe, String interests, int age, String gender, String status) {
        UserCard userCard = new UserCard();
        userCard.setAboutMe(aboutMe);
        userCard.setInterests(interests);
        userCard.setAge(age);
        userCard.setGender(gender);
        userCard.setStatus(status);
        userCard.setUserId(user);

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
