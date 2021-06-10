package ru.realdating.project.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.realdating.project.model.User;
import ru.realdating.project.model.UserCard;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class UserCardDaoTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private UserDao userDao;
    private UserCardDao userCardDao;

    @Before
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
        userDao = new UserDao(entityManager);
        userCardDao = new UserCardDao(entityManager);
    }

    @After
    public void tearDown() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    public void CreateAndFindUserCard() {
        User user = userDao.createUser("admin","admin");

        UserCard finalUserCard = userCardDao.createUserCard(user);

        assertEquals(user.getId(),finalUserCard.getUserId().getId());
        assertEquals(2, finalUserCard.getId());
        assertNotNull(finalUserCard);

        UserCard foundUserCard = userCardDao.findUserCard(user.getId());
        assertEquals(finalUserCard.getId(), foundUserCard.getId());
        assertNotNull(foundUserCard);

        UserCard foundUserCard2 = userCardDao.findUserCard(57);
        assertNull(foundUserCard2);

    }

    @Test
    public void refreshMainInfoUserCard() {
        User user = userDao.createUser("admin","admin");
        userCardDao.createUserCard(user);
        UserCard userCard = userCardDao.findUserCard(user.getId());

        userCard.setAboutMe("aboutMe");
        userCard.setInterests("interests");
        userCard.setAge(27);
        userCard.setGender("gender");
        userCard.setStatus("status");

        UserCard refreshedUserCard = userCardDao.refreshMainInfoUserCard(userCard);

        assertEquals(userCard.getId(), refreshedUserCard.getId());
        assertEquals(userCard.getAboutMe(), refreshedUserCard.getAboutMe());
        assertEquals(userCard.getInterests(), refreshedUserCard.getInterests());
        assertEquals(userCard.getAge(), refreshedUserCard.getAge());
        assertEquals(userCard.getGender(), refreshedUserCard.getGender());
        assertEquals(userCard.getStatus(), refreshedUserCard.getStatus());
        assertEquals(userCard.getUserId().getId(), refreshedUserCard.getUserId().getId());
    }

    @Test
    public void addLike() {
        User user = userDao.createUser("admin","admin");
        userCardDao.createUserCard(user);
        UserCard userCard = userCardDao.findUserCard(user.getId());
        UserCard finalUserCard = userCardDao.addLike(userCard);

        assertEquals(1, finalUserCard.getLikeUserCard());
    }

}