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

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/usercard")
public class UserCardController {

    // rus: контроллер отвечающий за карточку-профиль пользователя
    // eng: controller responsible for the user's profile card

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserCardDao userCardDao;

    @Autowired
    private UserCard userCard;

    @GetMapping("/edit-usercard")
    public String editUserCard(Model model, UserCardForm userCardForm, Authentication authentication) {
        model.addAttribute("userCardForm", userCardForm);
        model.addAttribute("getLogin", authentication.getName());
        return "/usercard/user_card";
    }

    // rus: изменить карточку пользователя
    // eng: change user card
    @PostMapping("/edit-usercard")
    public String handleEditUserCard(
            // внизу конструктор
            @ModelAttribute("userCardForm") @Valid UserCardForm userCardForm,
            BindingResult bindingResult,
            Authentication authentication,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("getLogin", authentication.getName());
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

    // rus: посмотреть карточку пользователя
    // eng: view user card
    @GetMapping("/look-profile")
    public String lookProfile(Model model, Authentication authentication) {
        User user = userDao.findUserByLogin(authentication.getName());
        UserCard userCardProfile = new UserCard();
        userCardProfile = userCardDao.findUserCard(user.getId());
        String imgBase64 = Base64.encodeBase64String(userCardProfile.getFoto());
        AvatarService avatarService = new AvatarService();

        model.addAttribute("login", user);
        model.addAttribute("userCardPhoto", imgBase64);
        model.addAttribute("userCard", userCardProfile);
        model.addAttribute("authentication", authentication.getName().equals(user.getLogin()));
        model.addAttribute("avatar", avatarService.checkAvatar(userCardProfile));
        return "/usercard/my-profile";
    }

    @ModelAttribute("userCardForm")
    public UserCardForm createDefault() {
        return new UserCardForm();
    }

}
