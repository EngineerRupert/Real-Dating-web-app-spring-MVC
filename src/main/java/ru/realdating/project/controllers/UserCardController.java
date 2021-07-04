package ru.realdating.project.controllers;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.realdating.project.dao.UserCardDao;
import ru.realdating.project.dao.UserDao;
import ru.realdating.project.model.User;
import ru.realdating.project.model.UserCard;
import ru.realdating.project.service.AvatarService;
import ru.realdating.project.service.UserCardForm;
import ru.realdating.project.service.UserSession;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/usercard")
//@SessionAttributes("userSession")
public class UserCardController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserCardDao userCardDao;

    @Autowired
    private UserCard userCard;

    @GetMapping("/edit-usercard")
    public String editUserCard(Model model,
                               UserCardForm userCardForm,
                               Authentication authentication) {
        model.addAttribute("userCardForm", userCardForm);
        model.addAttribute("getLogin", authentication.getName());
        return "/usercard/user_card";
    }

    @PostMapping("/edit-usercard")
    public String handleEditUserCard(
            @ModelAttribute("userCardForm")
            @Valid UserCardForm userCardForm,
            BindingResult bindingResult,
            Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "/usercard/user_card";
        }

        String userName = authentication.getName();
        User user = userDao.findUserByLogin(userName);

        userCard = userCardDao.findUserCard(user.getId());
        if (!userCardForm.getAboutMe().isEmpty()) {
            userCard.setAboutMe(userCardForm.getAboutMe());
        }
        if (!userCardForm.getInterests().isEmpty()) {
            userCard.setInterests(userCardForm.getInterests());
        }
        if (!userCardForm.getAge().isEmpty()) {
            userCard.setAge(userCardForm.getAge());
        }
        userCard.setStatus(userCardForm.getStatus());
        userCard.setGender(userCardForm.getGender());
        userCardDao.refreshMainInfoUserCard(userCard);
        return "redirect:/menu/user-menu";
    }
    // внизу конструктор

    @GetMapping("/look-profile")
    public String lookProfile(
            Model model,
            Authentication authentication) {

        String userName = authentication.getName();
        model.addAttribute("getLogin", userName);
        User user = userDao.findUserByLogin(userName);

        UserCard userCardProfile = new UserCard();
        userCardProfile = userCardDao.findUserCard(user.getId());
        String imgBase64 = Base64.encodeBase64String(userCardProfile.getFoto());
        AvatarService avatarService = new AvatarService();
        model.addAttribute("userCardPhoto", imgBase64);
        model.addAttribute("userCard", userCardProfile);
        model.addAttribute("avatar", avatarService.checkAvatar(userCardProfile));
        return "/usercard/my-profile";
    }

    @ModelAttribute("userCardForm")
    public UserCardForm createDefault() {
        return new UserCardForm();
    }

}
