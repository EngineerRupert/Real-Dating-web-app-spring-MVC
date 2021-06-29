package ru.realdating.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.realdating.project.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    @Transactional
    default User createUser(String login, String password) {
        User user = new User(login, password);
            save(user);
        return user;
    }

    @Query("select u from User u where u.login = :login")
    User findUserByLogin(@Param("login") String login);

    @Query("select u from User u where u.login = :login and u.password = :password")
    User findUserByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    // old without @Transactional
//    public User createUser(String login, String password) {
//        User user = new User(login, password);
//        entityManager.getTransaction().begin();
//        try {
//            entityManager.persist(user);
//            entityManager.getTransaction().commit();
//        } catch (Exception e) {
//            entityManager.getTransaction().rollback();
//            throw e;
//        }
//        return user;
//    }

    //old without Repository
//    default User findUserByLogin(String login) {
//        try {
//            return entityManager.createQuery("select u from User u where u.login = :loginForSearch", User.class)
//                    .setParameter("loginForSearch", login)
//                    .getSingleResult();
//        } catch (NoResultException e) {
//            return null;
//        }
//    }

    // old without Repository
//    default User findUserByLoginAndPassword(String login, String password) {
//        try {
//            return entityManager.createQuery("select u from User u " +
//                    "where u.login = :loginForSearch and u.password = :passwordForSearch", User.class)
//                    .setParameter("loginForSearch", login)
//                    .setParameter("passwordForSearch", password)
//                    .getSingleResult();
//        } catch (NoResultException e) {
//            return null;
//        }
//    }

}
