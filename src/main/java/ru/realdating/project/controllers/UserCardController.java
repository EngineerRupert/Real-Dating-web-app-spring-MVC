package ru.realdating.project.controllers;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.realdating.project.dao.UserCardDao;
import ru.realdating.project.model.UserCard;
import ru.realdating.project.service.AvatarService;
import ru.realdating.project.service.RegistrationForm;
import ru.realdating.project.service.UserCardForm;
import ru.realdating.project.service.UserSession;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/usercard")
@SessionAttributes("userSession")
public class UserCardController {

    @Autowired
    private UserCardDao userCardDao;

    @Autowired
    private UserCard userCard;

    @GetMapping("/edit-usercard")
    public String editUserCard(
            @ModelAttribute("userCardForm") UserCardForm userCardForm) {
        return "/usercard/user_card";
    }

    @PostMapping("/edit-usercard")
    public String handleEditUserCard(
            @ModelAttribute("userCardForm")
            @Valid UserCardForm userCardForm,
            BindingResult bindingResult,
            UserSession userSession) {
        if (bindingResult.hasErrors()) {
            return "/usercard/user_card";
        }
        userCard = userCardDao.findUserCard(userSession.getId());
        if (!(userCardForm.getAboutMe().isEmpty())) {
            userCard.setAboutMe(userCardForm.getAboutMe());
        }
        if (!(userCardForm.getInterests().isEmpty())) {
            userCard.setInterests(userCardForm.getInterests());
        }
        if (!(userCardForm.getAge().isEmpty())) {
            userCard.setAge(userCardForm.getAge());
        }
        userCard.setStatus(userCardForm.getStatus());
        userCard.setGender(userCardForm.getGender());
        userCardDao.refreshMainInfoUserCard(userCard);
        return "redirect:/menu/user-menu";
    }
    // внизу конструктор

    // Old PostMapping
//    @PostMapping("/edit-usercard")
//    public String handleEditUserCard(@RequestParam String aboutMe,
//                                     @RequestParam String interests,
//                                     @RequestParam String age,
//                                     @RequestParam String gender,
//                                     @RequestParam String status,
//                                     UserSession userSession) {
//
//        userCard = userCardDao.findUserCard(userSession.getId());
//        if (!(aboutMe.isEmpty())) {
//            userCard.setAboutMe(aboutMe);
//        }
//        if (!(interests.isEmpty())) {
//            userCard.setInterests(interests);
//        }
//        if (!(age.isEmpty())) {
//            userCard.setAge(age);
//        }
//        if (!(gender.isEmpty())) {
//            userCard.setStatus(status);
//        }
//        if (!(status.isEmpty())) {
//            userCard.setGender(gender);
//        }
//        userCardDao.refreshMainInfoUserCard(userCard);
//        return "redirect:/menu/user-menu";
//    }

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

    @ModelAttribute("userCardForm")
    public UserCardForm createDefault() {
        return new UserCardForm();
    }

}
