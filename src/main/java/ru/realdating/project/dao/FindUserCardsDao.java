package ru.realdating.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.realdating.project.model.UserCard;

import java.util.List;

@Repository
public interface FindUserCardsDao extends JpaRepository <UserCard, Integer> {

    // rus: DAO-репозитория отвечающая за поиск пользователей, выводит их для просмотра
    // eng: DAO repository responsible for finding users, displays them for viewing

    @Query("from UserCard where gender = 'Male'")
    List<UserCard> getUserCardsMale();

    @Query("from UserCard where gender = 'Female'")
    List<UserCard> getUserCardsFemale();

}
