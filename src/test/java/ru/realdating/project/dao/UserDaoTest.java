package ru.realdating.project.dao;

import org.junit.jupiter.api.BeforeEach;
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

    private User createdUser;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserDao userDao;

    @BeforeEach
    void setUp() {
        createdUser = userDao.createUser("admin", "pass");
    }

    @Test
    public void createAndFindUserById() {
        assertNotNull(createdUser);
        assertEquals(createdUser, entityManager.find(User.class, createdUser.getId()));
    }

    @Test
    public void findByLoginExisting() {
        assertNotNull(createdUser);
        assertEquals(createdUser, userDao.findUserByLogin("admin"));
    }

    @Test
    public void findByLoginNonExisting() {
        assertNotNull(createdUser);
        assertEquals(createdUser, userDao.findUserByLogin("admin"));
        assertNull(userDao.findUserByLogin("lop"));
    }

    @Test
    public void findUserByLoginAndPassword() {
        assertNotNull(createdUser);
        assertEquals(createdUser, userDao.findUserByLoginAndPassword("admin","pass"));
        assertNull(userDao.findUserByLoginAndPassword("aaa", "bbb"));
    }

    @Test
    public void finById() {
        User userForCheck = userDao.findById(createdUser.getId());
        assertEquals(createdUser.getId(), userForCheck.getId());
    }

}