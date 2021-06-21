package ru.realdating.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.realdating.project.service.UserSession;

@Controller
@RequestMapping(path = "/menu")
@SessionAttributes("userSession")
public class MenuController {

    @GetMapping("/user-menu")
    public String userMainMenu() {
        return "/menu/user_main_menu";
    }

    @GetMapping("/logout")
    public String logout(UserSession userSession) {
        userSession.clearSession();
        return "redirect:/";
    }
}
