package ru.realdating.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.realdating.project.model.UserCard;

import java.util.List;

@Repository
public interface FindUserCardsDao extends JpaRepository <UserCard, Integer> {

    // DAO-репозитория отвечающая за поиск пользователей, выводит их для просмотра

    @Query("from UserCard where gender = 'Male'")
    List<UserCard> getUserCardsMale();

}
