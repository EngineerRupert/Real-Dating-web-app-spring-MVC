package ru.realdating.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.realdating.project.dao.UserCardDao;
import ru.realdating.project.model.UserCard;

public class AvatarService {

    // класс отвечающий за проверку на наличие аватарки

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
