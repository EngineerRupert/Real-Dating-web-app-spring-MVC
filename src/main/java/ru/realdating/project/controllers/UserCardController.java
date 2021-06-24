package ru.realdating.project.controllers;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.realdating.project.dao.UserCardDao;
import ru.realdating.project.model.UserCard;
import ru.realdating.project.service.AvatarService;
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
                                     @RequestParam String age,
                                     @RequestParam String gender,
                                     @RequestParam String status,
                                     UserSession userSession) {

        userCard = userCardDao.findUserCard(userSession.getId());
        if (!(aboutMe.isEmpty())) {
            userCard.setAboutMe(aboutMe);
        }
        if (!(interests.isEmpty())) {
            userCard.setInterests(interests);
        }
        if (!(age.isEmpty())) {
            userCard.setAge(age);
        }
        if (!(gender.isEmpty())) {
            userCard.setStatus(status);
        }
        if (!(status.isEmpty())) {
            userCard.setGender(gender);
        }
        userCardDao.refreshMainInfoUserCard(userCard);
        return "redirect:/menu/user-menu";
    }

    @GetMapping("/look-profile")
    public String lookProfile(
            Model model,
            UserSession userSession) {
        UserCard userCardProfile = new UserCard();
        userCardProfile = userCardDao.findUserCard(userSession.getId());
        String imgBase64 = Base64.encodeBase64String(userCardProfile.getFoto());
        AvatarService avatarService = new AvatarService();
        model.addAttribute("userCardPhoto", imgBase64);
        model.addAttribute("userCard", userCardProfile);
        model.addAttribute("avatar", avatarService.checkAvatar(userCardProfile));
        return "/usercard/my-profile";
    }

}
