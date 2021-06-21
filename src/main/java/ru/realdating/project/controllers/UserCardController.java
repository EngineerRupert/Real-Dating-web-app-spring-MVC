package ru.realdating.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.realdating.project.dao.UserCardDao;
import ru.realdating.project.dao.UserDao;
import ru.realdating.project.model.User;
import ru.realdating.project.model.UserCard;
import ru.realdating.project.service.UserSession;

@Controller
@RequestMapping(path = "/usercard")
@SessionAttributes("userSession")
public class UserCardController {

    @Autowired
    private UserCardDao userCardDao;

    @Autowired
    private UserCard userCard;

    @GetMapping("/edit-usercard")
    public String editUserCard() {
        return "/usercard/user_card";
    }

    @PostMapping("/edit-usercard")
    public String handleEditUserCard(@RequestParam String aboutMe,
                                     @RequestParam String interests,
                                     @RequestParam int age,
                                     @RequestParam String gender,
                                     @RequestParam String status,
                                     UserSession userSession) {

        userCard = userCardDao.findUserCard(userSession.getId());
        userCard.setAboutMe(aboutMe);
        userCard.setInterests(interests);
        userCard.setAge(age);
        userCard.setStatus(status);
        userCard.setGender(gender);
        userCardDao.refreshMainInfoUserCard(userCard);
        return "redirect:/menu/user-menu";
    }

}
