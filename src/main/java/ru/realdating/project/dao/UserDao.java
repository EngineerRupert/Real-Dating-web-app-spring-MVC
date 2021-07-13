package ru.realdating.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.realdating.project.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    // DAO-репозитория отвечающая за пользователя

    @Transactional
    default User createUser(String login, String password) {
        User user = new User(login, password);
            save(user);
        return user;
    }

    @Query("select u from User u where u.login = :login")
    User findUserByLogin(@Param("login") String login);

    @Query("from User u where u.id = :id")
    User findById(@Param("id") int id);

    @Query("select u from User u where u.login = :login and u.password = :password")
    User findUserByLoginAndPassword(@Param("login") String login, @Param("password") String password);

}
