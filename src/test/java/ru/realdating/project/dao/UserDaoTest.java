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

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserDaoTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserDao userDao;

    @Test
    public void createAndFindUserById() {
        User createdUser = userDao.createUser("admin", "admin");
        assertNotNull(createdUser);
        assertEquals(createdUser, entityManager.find(User.class, createdUser.getId()));
    }

    @Test
    public void findByLoginExisting() {
        User createdUser = userDao.createUser("admin", "pass");
        assertEquals(createdUser, userDao.findUserByLogin("admin"));
    }

    @Test
    public void findByLoginNonExisting() {
        User createdUser = userDao.createUser("admin", "pass");
        assertEquals(createdUser, userDao.findUserByLogin("admin"));
        assertNull(userDao.findUserByLogin("lop"));
    }

    @Test
    public void findUserByLoginAndPassword() {
        User createdUser = userDao.createUser("admin", "pass");
        assertEquals(createdUser, userDao.findUserByLoginAndPassword("admin","pass"));
        assertNull(userDao.findUserByLoginAndPassword("aaa", "bbb"));
    }

}