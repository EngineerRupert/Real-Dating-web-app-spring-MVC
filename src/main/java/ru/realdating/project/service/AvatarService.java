package ru.realdating.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.realdating.project.dao.UserCardDao;
import ru.realdating.project.model.UserCard;

import javax.persistence.EntityManager;

public class AvatarService {

    @Autowired
    private UserCardDao userCardDao;

    public boolean checkAvatar(UserCard userCard) {
        if (userCard.getFoto() == null) {
            return false;
        } else {
            return true;
        }
    }

}
