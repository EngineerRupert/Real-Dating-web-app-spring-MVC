package ru.realdating.project.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.realdating.project.TestConfig;
import ru.realdating.project.config.AppConfig;
import ru.realdating.project.model.User;
import ru.realdating.project.model.UserCard;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserCardDaoTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserCardDao userCardDao;

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
        userCardDao.addLike(userCard);
        userCardDao.addLike(userCard);
        UserCard finalUserCard = userCardDao.addLike(userCard);

        assertEquals(3, finalUserCard.getLikeUserCard());
    }

}