package ru.realdating.project.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import ru.realdating.project.TestConfig;

import ru.realdating.project.model.User;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
        assertNotNull(createdUser);
        assertEquals(createdUser, userDao.findUserByLogin("admin"));
    }

    @Test
    public void findByLoginNonExisting() {
        User createdUser = userDao.createUser("admin", "pass");
        assertNotNull(createdUser);
        assertEquals(createdUser, userDao.findUserByLogin("admin"));
        assertNull(userDao.findUserByLogin("lop"));
    }

    @Test
    public void findUserByLoginAndPassword() {
        User createdUser = userDao.createUser("admin", "pass");
        assertNotNull(createdUser);
        assertEquals(createdUser, userDao.findUserByLoginAndPassword("admin","pass"));
        assertNull(userDao.findUserByLoginAndPassword("aaa", "bbb"));
    }

    @Test
    public void finById() {
        User user = userDao.createUser("admin","admin");
        User userForCheck = userDao.findById(user.getId());
        assertEquals(user.getId(), userForCheck.getId());
    }

}