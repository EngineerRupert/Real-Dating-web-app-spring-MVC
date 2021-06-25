package ru.realdating.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.realdating.project.dao.UserCardDao;
import ru.realdating.project.dao.UserDao;
import ru.realdating.project.model.User;
import ru.realdating.project.model.UserCard;
import ru.realdating.project.service.RegistrationForm;
import ru.realdating.project.service.UserSession;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/user")
@SessionAttributes("userSession")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserCardDao userCardDao;

    @GetMapping("/register")
    public String userRegister(@ModelAttribute("registrationForm") RegistrationForm registrationForm) {

        return "/user/user_register";
    }

    @PostMapping("/register")
    public String handleRegister(
            @ModelAttribute("registrationForm")
            @Valid RegistrationForm registrationForm,
            BindingResult bindingResult
            ) {

        if (!registrationForm.getPassword().equals(registrationForm.getPasswordConfirmation())) {
            bindingResult.addError(new FieldError(
                    "registrationForm",
                    "passwordConfirmation",
                    "Password and confirmation password should match"));
        }

        if (bindingResult.hasErrors()) {
            return "/user/user_register";
        }

        User userForCheck = userDao.findUserByLogin(registrationForm.getLogin());
        if (userForCheck == null) {
            userCardDao.createUserCard(userDao.createUser(registrationForm.getLogin(), registrationForm.getPassword()));
            return "redirect:/";
        } else {
            return "/user/user_register";
        }
    }

// Old Post without @Valid
//    @PostMapping("/register")
//    public String handleRegister(
//            @RequestParam String login,
//            @RequestParam String password
//    ) {
//        User userForCheck = userDao.findUserByLogin(login);
//        if (!(login.isEmpty() || password.isEmpty()) && userForCheck == null) {
//            User user = userDao.createUser(login, password);
//            userCardDao.createUserCard(user);
//            return "redirect:/";
//        }
//        return "redirect:/user/register";
//    }

    @GetMapping("/log-in")
    public String logIn() {
        return "/user/login";
    }

    @PostMapping("/log-in")
    public String handleLogIn(
            @RequestParam String login,
            @RequestParam String password,
            UserSession userSession
    ) {
        User user = userDao.findUserByLoginAndPassword(login, password);
        if (user != null) {
            userSession.setId(user.getId());
            userSession.setLogin(user.getLogin());
            return "redirect:/menu/user-menu";
        }
        userSession.clearSession();
        return "redirect:/user/log-in";
    }

    @ModelAttribute("registrationForm")
    public RegistrationForm createDefault() {
        return new RegistrationForm();
    }

}
