package ru.realdating.project.controllers;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.realdating.project.dao.FindUserCardsDao;
import ru.realdating.project.dao.UserCardDao;
import ru.realdating.project.dao.UserDao;
import ru.realdating.project.model.User;
import ru.realdating.project.model.UserCard;
import ru.realdating.project.service.AvatarService;
import ru.realdating.project.service.UsersCardsPageService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LookUserCards {

    // rus: контроллер отвечающий за просмотр карточек пользователей
    // eng: controller responsible for viewing user cards

    @Autowired
    private FindUserCardsDao userCardsDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserCardDao userCardDao;

    @GetMapping("/look-users")
    public String lookUsersFilter(
            Authentication authentication,
            Model model) {
        model.addAttribute("getLogin", authentication.getName());
        return "/usercard/users-filter";
    }

    @PostMapping("/look-users")
    public String handleLookUsersFilter(@RequestParam String gender) {
        return "redirect:/look-users/" + gender;
    }

    // rus: просмотр(список) карточек пользователей
    // eng: view (list) of user cards
    @GetMapping("/look-users/{gender}")
    public String lookUserCards(
            @PathVariable("gender") String gender,
            Authentication authentication,
            Model model) {

        List<UserCard> listOfUserCards = new ArrayList<>();
        if (gender.equals("Male")) {
            listOfUserCards = userCardsDao.getUserCardsMale();
        }
        if (gender.equals("Female")) {
            listOfUserCards = userCardsDao.getUserCardsFemale();
        }

        String[] imgBase64 = new String[listOfUserCards.size()];
        List<UsersCardsPageService> listOfUsers = new ArrayList<>();
        List<Integer> listOfId = new ArrayList<>();

        for (int i = 0; i < imgBase64.length; i++) {
            imgBase64[i] = Base64.encodeBase64String(listOfUserCards.get(i).getFoto());

            // rus: специальный класс (типа User), но только с нужными полями (из 2-х таблиц)
            // eng: a special class (of the User type), but only with the required fields (from 2 tables)
            UsersCardsPageService usersCardsPageService = new UsersCardsPageService();

            // rus: записываем в эти поля нужные нам значения пользователя
            // eng: we write in these fields the user values we need
            usersCardsPageService.setId(listOfUserCards.get(i).getUserId().getId());
            usersCardsPageService.setLogin(userDao.findById(listOfUserCards.get(i).getUserId().getId()).getLogin());
            usersCardsPageService.setGender(listOfUserCards.get(i).getGender());
            usersCardsPageService.setStatus(listOfUserCards.get(i).getStatus());
            usersCardsPageService.setAge(listOfUserCards.get(i).getAge());

            // rus: добавляем в коллекцию наших юзеров
            // eng: add our users to the collection
            listOfUsers.add(usersCardsPageService);
        }

        listOfUserCards.clear();

        model.addAttribute("userCardPhoto", imgBase64);
        model.addAttribute("listOfUserCards", listOfUsers);
        model.addAttribute("getLogin", authentication.getName());
        return "/usercard/show_user_cards";
    }

    // rus: переход из списка карточек пользователей в индивидуальный просмотр
    // eng: transition from the list of user cards to individual view
    @GetMapping("/user/{id}/profile")
    public String lookUserProfile(
            @PathVariable("id") int id,
            Model model,
            Authentication authentication) {

        User user = userDao.findById(id);
        UserCard userCardProfile = userCardDao.findUserCard(user.getId());
        String imgBase64 = Base64.encodeBase64String(userCardProfile.getFoto());
        AvatarService avatarService = new AvatarService();

        model.addAttribute("userCardPhoto", imgBase64);
        model.addAttribute("userCard", userCardProfile);
        model.addAttribute("login", user);
        model.addAttribute("authentication", authentication.getName().equals(user.getLogin()));
        model.addAttribute("avatar", avatarService.checkAvatar(userCardProfile));
        return "/usercard/my-profile";
    }

    @PostMapping("/add-like/{id}")
    public String addLike(@PathVariable("id") int id) {
        UserCard userCard = userCardDao.findUserCard(id);
        userCardDao.addLike(userCard);
        return "redirect:/user/" + id + "/profile";
    }

}
