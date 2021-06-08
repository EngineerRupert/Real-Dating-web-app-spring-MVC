package ru.realdating.project.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.realdating.project.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class UserDaoTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private UserDao userDao;

    @Before
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
        userDao = new UserDao(entityManager);
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