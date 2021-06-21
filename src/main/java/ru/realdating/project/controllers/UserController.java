package ru.realdating.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.realdating.project.dao.UserCardDao;
import ru.realdating.project.dao.UserDao;
import ru.realdating.project.model.User;
import ru.realdating.project.service.UserSession;

@Controller
@RequestMapping(path = "/user")
@SessionAttributes("userSession")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserCardDao userCardDao;

    @GetMapping("/register")
    public String userRegister() {
        return "/user/user_register";
    }

    @PostMapping("/register")
    public String handleRegister(
            @RequestParam String login,
            @RequestParam String password
    ) {
        User user = userDao.createUser(login, password);
        userCardDao.createUserCard(user);
        return "redirect:/";
    }

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
        return "redirect:/user/log-in";
    }

}
