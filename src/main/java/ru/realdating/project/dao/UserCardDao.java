package ru.realdating.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.realdating.project.model.User;
import ru.realdating.project.model.UserCard;

@Repository
public interface UserCardDao extends JpaRepository<UserCard, Integer> {

    // rus: DAO-репозитория отвечающая за карточку-профиль пользователя
    // eng: DAO repository responsible for the user profile card

    @Query("from UserCard u where u.userId.id = :userId")
    UserCard findUserCard(@Param("userId") int id);

    @Transactional
    default UserCard createUserCard(User user) {
        UserCard userCard = new UserCard();
            save(userCard);
            userCard.setUserId(user);
        return userCard;
    }

    @Transactional
    default UserCard refreshMainInfoUserCard(UserCard userCard) {
        save(userCard);
        return userCard;
    }

    @Transactional
    default UserCard addLike(UserCard userCard) {
        userCard.setLikeUserCard(userCard.getLikeUserCard()+1);
        save(userCard);
        return userCard;
    }

}
